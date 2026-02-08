package com.example.user_service.service.impl;

import com.example.user_service.dto.CreateUserDto;
import com.example.user_service.service.KeycloakService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {
    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;
    @Override
    public String createUserAndAssignRole(CreateUserDto dto) {
        RealmResource realmResource = keycloak.realm(realm);

        // 1️⃣ Ensure role exists
        String roleName = dto.getUserType().getTypeName();
        ensureRealmRoleExists(realmResource, roleName);

        // 2️⃣ Create user
        UserRepresentation user = new UserRepresentation();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getUsername());
        user.setLastName(dto.getUsername());
        user.setEnabled(true);
        user.setEmailVerified(true);

        CredentialRepresentation password = new CredentialRepresentation();
        password.setType(CredentialRepresentation.PASSWORD);
        password.setValue(dto.getPassword());
        password.setTemporary(false);

        user.setCredentials(List.of(password));

        Response response = realmResource.users().create(user);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Create user failed. Status: " + response.getStatus());
        }

        String userId = CreatedResponseUtil.getCreatedId(response);

        // 3️⃣ Assign role to user
        RoleRepresentation role = realmResource
                .roles()
                .get(roleName)
                .toRepresentation();

        realmResource.users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(List.of(role));

        log.info("Created user '{}' with role '{}'", dto.getUsername(), roleName);

        return userId;
    }

    private void ensureRealmRoleExists(RealmResource realmResource, String roleName) {

        boolean exists = realmResource.roles()
                .list()
                .stream()
                .anyMatch(r -> r.getName().equals(roleName));

        if (exists) {
            return;
        }

        RoleRepresentation role = new RoleRepresentation();
        role.setName(roleName);
        role.setDescription("Auto-created role: " + roleName);

        realmResource.roles().create(role);

        log.info("Created realm role '{}'", roleName);
    }
}
