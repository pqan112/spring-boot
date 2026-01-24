package com.example.demo.dto.req.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateReqDTO {
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
