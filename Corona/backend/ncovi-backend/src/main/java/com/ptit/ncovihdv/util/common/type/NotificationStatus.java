package com.ptit.ncovihdv.util.common.type;

/**
 * 19-Jun-2020
 */
public enum NotificationStatus {
    WAIT(0),
    DONE(1);

    private int value;

    NotificationStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
