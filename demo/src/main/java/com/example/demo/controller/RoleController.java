package com.example.demo.controller;

import com.example.demo.dto.req.role.RoleReq;
import com.example.demo.dto.res.ApiResponse;
import com.example.demo.dto.res.role.RoleRes;
import com.example.demo.service.RoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
        RoleService roleService;

        public RoleController(RoleService roleService) {
            this.roleService = roleService;
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public ApiResponse<RoleRes> create(@RequestBody RoleReq req) {
            return ApiResponse.<RoleRes>builder()
                    .status(HttpStatus.CREATED.value())
                    .message("message.create_role_success")
                    .data(roleService.create(req))
                    .build();
        }

        @GetMapping
        public ApiResponse<List<RoleRes>> getAll() {
            return ApiResponse.<List<RoleRes>>builder()
                    .status(HttpStatus.OK.value())
                    .message("message.get_roles_success")
                    .data(roleService.list())
                    .build();
        }

        @DeleteMapping("/{role}")
        public ApiResponse<Void> delete(@RequestParam String role) {
            roleService.delete(role);
            return ApiResponse.<Void>builder()
                    .status(HttpStatus.OK.value())
                    .message("message.delete_role_success")
                    .build();
        }
    }

