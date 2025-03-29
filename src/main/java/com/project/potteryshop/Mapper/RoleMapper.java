package com.project.potteryshop.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.potteryshop.Dto.Request.Authorize.RoleRequest;
import com.project.potteryshop.Dto.Request.Authorize.RoleUpdateRequest;
import com.project.potteryshop.Dto.Response.Authorize.RoleResponse;
import com.project.potteryshop.Entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "name", ignore = true)
    Role toRoleFromUpdate(RoleUpdateRequest request);

    RoleResponse toRoleResponse(Role role);
}
