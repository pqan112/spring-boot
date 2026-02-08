package com.example.user_service.dto;

import com.example.user_service.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserDto {
    String username;
    String email;
    String password;
    String firstName;
    String lastName;
    UserType userType;
}
