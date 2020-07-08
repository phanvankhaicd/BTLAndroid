package com.ptit.ncovihdv.util.common.type;

public enum HealthType {
    SAFE(1),
    BAD(0);

    private int value;

    HealthType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
