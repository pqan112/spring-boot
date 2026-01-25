package com.example.demo.mapper;

import com.example.demo.dto.req.role.RoleReq;
import com.example.demo.dto.res.role.RoleRes;
import com.example.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target="permissions", ignore = true)
    Role toRole(RoleReq req);
    RoleRes toRoleResponse(Role role);
}
