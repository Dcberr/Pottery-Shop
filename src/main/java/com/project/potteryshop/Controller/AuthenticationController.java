package com.project.potteryshop.Controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.Authentication.AuthenticationRequest;
import com.project.potteryshop.Dto.Request.Authentication.IntrospectRequest;
import com.project.potteryshop.Dto.Request.Authentication.LogoutRequest;
import com.project.potteryshop.Dto.Request.Authentication.RefreshRequest;
import com.project.potteryshop.Dto.Response.Authentication.AuthenticationResponse;
import com.project.potteryshop.Dto.Response.Authentication.IntrospectResponse;
import com.project.potteryshop.Service.AuthenticationService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .code(200)
                .message("Authenticated!!!")
                .result(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws JOSEException, ParseException {
        return ApiResponse.<IntrospectResponse>builder()
                .code(200)
                .message("Introspected Successfull!!!")
                .result(authenticationService.introspect(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws Exception {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().code(200).build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request) throws Exception {
        return ApiResponse.<AuthenticationResponse>builder()
                .code(200)
                .message("Authenticated!!!")
                .result(authenticationService.refreshToken(request))
                .build();
    }

    @PostMapping("/forgot-password")
    public ApiResponse<Void> forgotPassword(@RequestParam String username) throws MessagingException {
        authenticationService.forgotPassword(username);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Sent Reset Mail Successful!!!")
                .build();
    }
}
