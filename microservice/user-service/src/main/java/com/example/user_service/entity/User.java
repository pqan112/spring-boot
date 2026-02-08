package com.example.user_service.entity;

import com.example.user_service.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String keycloakId;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    UserType userType;

}
