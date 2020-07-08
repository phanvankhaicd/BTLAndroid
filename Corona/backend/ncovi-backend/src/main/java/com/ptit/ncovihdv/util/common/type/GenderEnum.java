package com.ptit.ncovihdv.util.common.type;

/**
 * 07-Jun-2020
 */
public enum GenderEnum {
    MALE(1),
    FEMALE(2);

    private int value;

    GenderEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
