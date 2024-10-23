package com.pidw.sindPro.domains.users;

public enum UserRole {

    RESIDENT("resident"),
    DOORMAN("doorman"),
    ADMIN("admin");

    private String role;

    UserRole(String role) {
        role = role;
    }

    public String getRole() {
        return role;
    }
}
