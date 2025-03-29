package com.project.potteryshop.Mapper;

import org.mapstruct.Mapper;
import com.project.potteryshop.Dto.Request.Authorize.PermissionRequest;
import com.project.potteryshop.Dto.Response.Authentication.PermissionResponse;
import com.project.potteryshop.Entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
