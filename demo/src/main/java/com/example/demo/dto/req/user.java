package com.example.demo.dto.req;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

public class user {
    @Getter
    @Setter
    public static class UserReqDTO {
        private String username;
        @Size(min = 6, message = "error.password_size_error")
        private String password;
        private String firstName;
        private String lastName;
        private LocalDate dob;
        Set<String> roles;
    }
}
