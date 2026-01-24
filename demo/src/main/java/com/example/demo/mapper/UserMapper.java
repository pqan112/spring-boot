package com.example.demo.mapper;

import com.example.demo.dto.req.user.UserReqDTO;
import com.example.demo.dto.res.user.UserResponse;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserReqDTO req);

    UserResponse toResponseDTO(User user);
}
