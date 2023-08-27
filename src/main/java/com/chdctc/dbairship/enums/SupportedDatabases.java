package com.chdctc.dbairship.enums;

public enum SupportedDatabases {
    POSTGRESQL(0),
    MYSQL(1),
    ORACLE(2);

    private final int value;

    SupportedDatabases(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

