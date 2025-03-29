package com.project.potteryshop.Service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.Authorize.RoleRequest;
import com.project.potteryshop.Dto.Request.Authorize.RoleUpdateRequest;
import com.project.potteryshop.Dto.Response.Authorize.RoleResponse;
import com.project.potteryshop.Entity.Permission;
import com.project.potteryshop.Entity.Role;
import com.project.potteryshop.Mapper.RoleMapper;
import com.project.potteryshop.Repository.PermissionRepository;
import com.project.potteryshop.Repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse createRole(RoleRequest request) {
        Role role = roleMapper.toRole(request);

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse update(String roleName, RoleUpdateRequest request) {
        Role role = roleRepository.findById(roleName).orElseThrow();

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role.setDescription(request.getDescription());

        roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
