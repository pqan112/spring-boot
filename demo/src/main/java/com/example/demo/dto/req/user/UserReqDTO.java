package com.example.demo.dto.req.user;

import com.example.demo.entity.Role;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserReqDTO {
    private String username;
    @Size(min = 6, message = "error.password_size_error")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
//    Set<Role> roles;
}
