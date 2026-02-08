package com.example.user_service.enums;

public enum UserType {

    USER("USER"),
    ADMIN("ADMIN");

    private final String typeName;

    UserType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}

