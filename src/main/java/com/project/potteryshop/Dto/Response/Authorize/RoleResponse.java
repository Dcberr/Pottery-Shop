package com.project.potteryshop.Dto.Response.Authorize;

import java.util.Set;

import com.project.potteryshop.Entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {
    private String name;

    private String description;

    private Set<Permission> permissions;
}
