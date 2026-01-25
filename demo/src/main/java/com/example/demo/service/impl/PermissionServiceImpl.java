package com.example.demo.service.impl;

import com.example.demo.dto.req.permission.PermissionReq;
import com.example.demo.dto.res.permission.PermissionRes;
import com.example.demo.entity.Permission;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }


    @Override
    public PermissionRes create(PermissionReq req) {
        Permission permission = permissionMapper.toPermission(req);
        Permission savedPermission = permissionRepository.save(permission);
        return permissionMapper.toPermissionRes(savedPermission);
    }

    @Override
    public List<PermissionRes> list() {
        return permissionRepository
                .findAll()
                .stream()
                .map(permissionMapper::toPermissionRes)
                .toList();
    }

    @Override
    public void delete(String name) {
        permissionRepository.deleteById(name);
    }
}
