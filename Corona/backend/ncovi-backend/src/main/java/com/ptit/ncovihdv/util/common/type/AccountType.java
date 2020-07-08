package com.ptit.ncovihdv.util.common.type;

/**
 * 03-Jun-2020
 */
public enum AccountType {
    FACEBOOK(2),
    GOOGLE(1),
    NORMAL(0);

    private int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
