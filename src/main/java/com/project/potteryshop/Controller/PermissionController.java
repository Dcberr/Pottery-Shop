package com.project.potteryshop.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.Authorize.PermissionRequest;
import com.project.potteryshop.Dto.Response.Authentication.PermissionResponse;
import com.project.potteryshop.Service.PermissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .code(200)
                .message("Create Permission " + request.getName() + " Successfull!!!")
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(200)
                .message("Get All Permissions Successfull!!!")
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{name}")
    public ApiResponse<Void> create(@PathVariable String name) {
        permissionService.delete(name);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Delete Permission " + name + " Successfull!!!")
                .build();
    }

}
