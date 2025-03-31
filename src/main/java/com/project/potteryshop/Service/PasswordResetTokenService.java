package com.project.potteryshop.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Entity.PasswordResetToken;
import com.project.potteryshop.Repository.PasswordResetTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${jwt.reset-duration}")
    protected Long RESET_DURATION;

    public String createToken(String email) {
        String token = UUID.randomUUID().toString();

        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .token(token)
                .email(email)
                .expiryDate(new Date(Instant.now().plus(RESET_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .build();

        passwordResetTokenRepository.save(passwordResetToken);

        return token;
    }

    public Optional<PasswordResetToken> validateToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .filter(resetToken -> resetToken.getExpiryDate().after(new Date()));
    }
}
