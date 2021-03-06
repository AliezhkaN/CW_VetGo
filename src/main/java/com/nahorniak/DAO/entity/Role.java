package com.nahorniak.DAO.entity;

public enum Role {
    CUSTOMER("CUSTOMER"), ADMIN("ADMIN"),DOCTOR("DOCTOR"), UNKNOWN("UNKNOWN");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean equalsTo(String name) {
        return value.equals(name);
    }
}
