package com.example.demo.dto.res.role;

import com.example.demo.dto.res.permission.PermissionRes;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRes {
    String name;
    String description;
    Set<PermissionRes> permissions;
}
