package com.project.potteryshop.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project.potteryshop.Dto.Request.Authentication.AuthenticationRequest;
import com.project.potteryshop.Dto.Request.Authentication.EmailReceiverRequest;
import com.project.potteryshop.Dto.Request.Authentication.IntrospectRequest;
import com.project.potteryshop.Dto.Request.Authentication.LogoutRequest;
import com.project.potteryshop.Dto.Request.Authentication.RefreshRequest;
import com.project.potteryshop.Dto.Response.Authentication.AuthenticationResponse;
import com.project.potteryshop.Dto.Response.Authentication.IntrospectResponse;
import com.project.potteryshop.Entity.InvalidatedToken;
import com.project.potteryshop.Entity.User;
import com.project.potteryshop.Enum.UserStatus;
import com.project.potteryshop.Repository.InvalidatedTokenRepository;
import com.project.potteryshop.Repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final InvalidatedTokenRepository invalidatedTokenRepository;
    private final JavaMailSender mailSender;
    private final PasswordResetTokenService passwordResetTokenService;
    // private final String SIGNERKEY =

    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info(request.getUsername());
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String token = new String();
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            token = generateToken(user);
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Authenticated Failed!!!");
        }

        return AuthenticationResponse.builder().token(token).build();

    }

    private String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("Dcberr")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();

        boolean isValid = true;

        try {
            verifyToken(token, false);
        } catch (Exception e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(user.getUserRole())) {
            user.getUserRole().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permissions -> {
                        stringJoiner.add(permissions.getName());
                    });
                }
            });
        }
        return stringJoiner.toString();
    }

    public void logout(LogoutRequest request) throws Exception {
        SignedJWT signToken = verifyToken(request.getToken(), true);

        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
        String username = signToken.getJWTClaimsSet().getSubject();
        User user = userRepository.findByUsername(username).orElseThrow(null);

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);

        user.setLastLogin(new Date(Instant.now().toEpochMilli()));
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws Exception {
        SignedJWT signedJWT = verifyToken(request.getToken(), true);

        String jid = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jid)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);

        String username = signedJWT.getJWTClaimsSet().getSubject();

        User user = userRepository.findByUsername(username).orElseThrow();

        String token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws Exception {
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expriryTime = (isRefresh) ? new Date(signedJWT
                .getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        Boolean verified = signedJWT.verify(jwsVerifier);

        if (!(verified && expriryTime.after(new Date()))) {
            throw new Exception("Invalidated Token");
        }

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new Exception("Invalidated Token");
        }

        return signedJWT;
    }

    public void sendResetPasswordEmail(EmailReceiverRequest request) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setTo(request.getToEmail());
        messageHelper.setSubject("[POTTERY SHOP] RESET YOUR PASSWORD");
        messageHelper.setText("<p>Click the link below to reset your password:</p>"
                + "<a href=\"" + request.getResetLink() + "\">Reset Password</a>", true);

        mailSender.send(message);
    }

    public void forgotPassword(String username) throws MessagingException {
        User user = userRepository.findByUsername(username).orElseThrow();

        String email = user.getEmail();
        EmailReceiverRequest emailReceiverRequest = EmailReceiverRequest.builder().toEmail(email).resetLink(
                "http://localhost:8080/api/auth/reset-password?token=" + passwordResetTokenService.createToken(email))
                .build();

        sendResetPasswordEmail(emailReceiverRequest);
    }
}
