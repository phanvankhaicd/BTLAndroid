package com.ptit.ncovihdv.util.common.type;

public enum ReflectionType {
    CONTACT(1), INFO(0);

    private int value;

    ReflectionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
