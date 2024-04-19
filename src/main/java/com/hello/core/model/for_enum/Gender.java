package com.hello.core.model.for_enum;

public enum Gender {
    Female("Female"),
    Male("Male");

    private String value;

    public String getValue() {
        return value;
    }

    private Gender(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
