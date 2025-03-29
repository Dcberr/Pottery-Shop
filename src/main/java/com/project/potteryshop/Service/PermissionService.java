package com.project.potteryshop.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.potteryshop.Dto.Request.Authorize.PermissionRequest;
import com.project.potteryshop.Dto.Response.Authentication.PermissionResponse;
import com.project.potteryshop.Entity.Permission;
import com.project.potteryshop.Mapper.PermissionMapper;
import com.project.potteryshop.Repository.PermissionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
