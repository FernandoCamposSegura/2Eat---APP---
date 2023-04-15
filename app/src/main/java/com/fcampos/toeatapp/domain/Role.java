package com.fcampos.toeatapp.domain;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN"),
    ESTABLISHMENT("ESTABLISHMENT");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


