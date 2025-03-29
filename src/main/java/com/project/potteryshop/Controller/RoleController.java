package com.project.potteryshop.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Dto.Request.Authorize.RoleRequest;
import com.project.potteryshop.Dto.Request.Authorize.RoleUpdateRequest;
import com.project.potteryshop.Dto.Response.Authorize.RoleResponse;
import com.project.potteryshop.Service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .message("Create Role " + request.getName() + " Successful!!!")
                .result(roleService.createRole(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .code(200)
                .message("Get All Successful!!!")
                .result(roleService.getAll())
                .build();
    }

    @PutMapping("/{roleName}")
    public ApiResponse<RoleResponse> update(@PathVariable String roleName, @RequestBody RoleUpdateRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .message("Update Role " + roleName + " Successful!!!")
                .result(roleService.update(roleName, request))
                .build();
    }

    @DeleteMapping("/{role}")
    public ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Delete Role " + role + " Successful!!!")
                .build();
    }
}
