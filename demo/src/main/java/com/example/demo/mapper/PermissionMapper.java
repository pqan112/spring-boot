package com.example.demo.mapper;

import com.example.demo.dto.req.permission.PermissionReq;
import com.example.demo.dto.res.permission.PermissionRes;
import com.example.demo.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionReq req);
    PermissionRes toPermissionRes(Permission permission);

}
