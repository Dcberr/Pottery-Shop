package com.project.potteryshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.UserCreateRequest;
import com.project.potteryshop.Dto.Response.UserCreateResponse;
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
    public ApiResponse<UserCreateResponse> createUser(UserCreateRequest newUser) {
        return ApiResponse.<UserCreateResponse>builder()
                .code(200)
                .message("Created Successful!!!")
                .result(userService.createUser(newUser))
                .build();
    }

}
