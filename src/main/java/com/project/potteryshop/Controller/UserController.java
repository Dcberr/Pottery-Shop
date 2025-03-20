package com.project.potteryshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.User.UserCreateRequest;
import com.project.potteryshop.Dto.Request.User.UserUpdateRequest;
import com.project.potteryshop.Dto.Response.User.UserCreateResponse;
import com.project.potteryshop.Dto.Response.User.UserResponse;
import com.project.potteryshop.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
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

    @PutMapping
    public ApiResponse<UserResponse> updateUser(@RequestParam String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("Updated Successful!!!")
                .result(userService.updateUser(userId, request))
                .build();
    }

}
