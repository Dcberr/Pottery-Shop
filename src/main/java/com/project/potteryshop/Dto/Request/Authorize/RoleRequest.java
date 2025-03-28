package com.project.potteryshop.Dto.Request.Authorize;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {
    private String name;

    private String description;

    private Set<String> permissions;
}
