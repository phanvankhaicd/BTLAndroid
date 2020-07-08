package com.ptit.ncovihdv.util.common.type;

/**
 * 19-Jun-2020
 */
public enum NotificationType {
    SINGLE(1),
    ALL(2);

    private int value;

    NotificationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
