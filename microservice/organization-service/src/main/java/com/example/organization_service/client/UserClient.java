package com.example.organization_service.client;

import com.example.organization_service.config.FeignClientInterceptorConfig;
import com.example.organization_service.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", configuration = FeignClientInterceptorConfig.class)
public interface UserClient {

    @GetMapping("/api/users/keycloak/{sub}")
    UserResponse getUserByKeycloakId(@PathVariable("sub") String keycloakId);
}
