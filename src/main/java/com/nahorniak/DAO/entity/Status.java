package com.nahorniak.DAO.entity;

public enum Status {
    ACTIVE("ACTIVE"),CLOSED("CLOSED");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean equalsTo(String name) {
        return value.equals(name);
    }
}
