package com.example.demo.config;

import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
public class ApplicationInitConfig {
    private final PasswordEncoder passwordEncoder;

    public ApplicationInitConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
           if(userRepository.findByUsername("admin").isEmpty()) {
               HashSet<String> roles = new HashSet<>();
               roles.add(Role.ADMIN.name());
               User user = new User();
               user.setUsername("admin");
               user.setPassword(passwordEncoder.encode("admin"));
//               user.setRoles(roles);
               userRepository.save(user);
               log.warn("admin user has been created with default password admin");
           }
        };
    }

}
