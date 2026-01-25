package com.example.demo.controller;


import com.example.demo.dto.req.permission.PermissionReq;
import com.example.demo.dto.res.ApiResponse;
import com.example.demo.dto.res.permission.PermissionRes;
import com.example.demo.service.PermissionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PermissionRes> create(@RequestBody PermissionReq req) {
        return ApiResponse.<PermissionRes>builder()
                .status(HttpStatus.CREATED.value())
                .message("message.create_permission_success")
                .data(permissionService.create(req))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionRes>> getAll() {
        return ApiResponse.<List<PermissionRes>>builder()
                .status(HttpStatus.OK.value())
                .message("message.get_permissions_success")
                .data(permissionService.list())
                .build();
    }

    @DeleteMapping("/{permission}")
    public ApiResponse<Void> delete(@RequestParam String permission) {
        permissionService.delete(permission);
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("message.delete_permission_success")
                .build();
    }
}
