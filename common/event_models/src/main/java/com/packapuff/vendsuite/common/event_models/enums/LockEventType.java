package com.packapuff.vendsuite.common.event_models.enums;

public enum LockEventType {
    LOCKED("locked"),
    UNLOCKED("unlocked"),
    UNKNOWN("unknown");

    private final String value;
    private LockEventType(String value) {
        this.value = value.toLowerCase();
    }
    public String getValue() {
        return value;
    }
}
