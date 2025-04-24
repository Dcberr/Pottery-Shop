package com.project.potteryshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.Authentication.ResetPasswordRequest;
import com.project.potteryshop.Dto.Request.User.UserCreateRequest;
import com.project.potteryshop.Dto.Request.User.UserUpdateRequest;
import com.project.potteryshop.Dto.Response.User.UserCreateResponse;
import com.project.potteryshop.Dto.Response.User.UserResponse;
import com.project.potteryshop.Entity.User;
import com.project.potteryshop.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<UserCreateResponse> createUser(@RequestBody UserCreateRequest newUser) {
        return ApiResponse.<UserCreateResponse>builder()
                .code(200)
                .message("Created Successful!!!")
                .result(userService.createUser(newUser))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .code(200)
                .message("Get all users successful!!!")
                .result(userService.getAllUser())
                .build();
    }

    @GetMapping("/info")
    public ApiResponse<UserResponse> getMyInfor() {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("Get Infor Succesful!!!")
                .result(userService.getMyInfor())
                .build();
    }

    @PutMapping
    public ApiResponse<UserResponse> updateUser(@RequestParam String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("Updated Successful!!!")
                .result(userService.updateUser(userId, request))
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String userId) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("Get User " + userId + " Successful!!!")
                .result(userService.getUserById(userId))
                .build();
    }

    @PostMapping("/reset")
    public ApiResponse<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Reset Password Successful!!!")
                .build();
    }

    @GetMapping("/online")
    public ApiResponse<List<UserResponse>> getOnlineUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .code(200)
                .message("Get All Online Users Successfull!!!")
                .result(userService.getUserOnline())
                .build();
    }

    @MessageMapping("/user.connectUser")
    @SendTo("/user/public")
    public ApiResponse<Void> connectUser(@Payload User user) {
        userService.connectUser(user);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("User " + user.getName() + " is connected!!!")
                .build();
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public ApiResponse<Void> disconnectUser(@Payload User user) {
        userService.disconnect(user);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("User " + user.getName() + " is disconnected!!!")
                .build();
    }
}
