package com.packapuff.vendsuite.common.event_models.enums;

public enum EventResourceType {

    FLEET("fleet"),
    MACHINE("machine"),
    POINT_OF_SALE("point_of_sale"),
    PRODUCT("product"),
    USER("user"),
    MACHINE_CONNECTION("machine_connection");

    private final String value;
    private EventResourceType(String value) {
        this.value = value.toLowerCase();
    }
    public String getValue() {
        return value;
    }
}
