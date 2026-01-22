package com.example.demo.mapper;

import com.example.demo.dto.req.auth.AuthenticationReqDTO;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {
    User toUser(AuthenticationReqDTO reqDTO);
}
