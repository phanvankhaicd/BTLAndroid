package com.ptit.ncovihdv.util.common.type;

/**
 * 07-Jun-2020
 */
public enum StatusEnum {
    DELETE(-1),
    ACTIVE(1),
    //notify
    WAIT(2),
    COMPLETE(3);

    private int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
