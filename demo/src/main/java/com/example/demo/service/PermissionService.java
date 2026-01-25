package com.example.demo.service;

import com.example.demo.dto.req.permission.PermissionReq;
import com.example.demo.dto.res.permission.PermissionRes;

import java.util.List;

public interface PermissionService {
    PermissionRes create(PermissionReq req);
    List<PermissionRes> list();
    void delete(String name);
}
