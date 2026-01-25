package com.example.demo.service;

import com.example.demo.dto.req.role.RoleReq;
import com.example.demo.dto.res.role.RoleRes;

import java.util.List;

public interface RoleService {
    RoleRes create(RoleReq req);
    List<RoleRes> list();

    void delete(String name);
}
