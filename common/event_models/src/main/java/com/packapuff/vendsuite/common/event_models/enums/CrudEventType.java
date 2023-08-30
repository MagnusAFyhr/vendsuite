package com.packapuff.vendsuite.common.event_models.enums;

public enum CrudEventType {
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted");

    private final String value;
    private CrudEventType(String value) {
        this.value = value.toLowerCase();
    }
    public String getValue() {
        return value;
    }
}