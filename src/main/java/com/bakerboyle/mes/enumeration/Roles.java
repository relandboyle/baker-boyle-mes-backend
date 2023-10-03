package com.bakerboyle.mes.enumeration;

public enum Roles {
    OPERATOR("OPERATOR"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private final String roles;

    Roles(String roles) {
        this.roles = roles;
    }

    public String getRoles() {
        return this.roles;
    }
}
