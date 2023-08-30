package com.packapuff.vendsuite.common.event_models.enums;

public enum ConnectionEventType {
    CONNECTED("connected"),
    DISCONNECTED("disconnected"),
    UNKNOWN("unknown");

    private final String value;
    private ConnectionEventType(String value) {
        this.value = value.toLowerCase();
    }
    public String getValue() {
        return value;
    }
}
