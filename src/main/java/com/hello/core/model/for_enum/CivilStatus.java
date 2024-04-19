package com.hello.core.model.for_enum;

public enum CivilStatus {
    Single("Single"),
    Married("Married"),
    Widowed("Widowed"),
    Separated("Separated");

    private String value;

    public String getValue() {
        return value;
    }

    private CivilStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
