package com.packapuff.services.events.service.enums;

public enum EventServiceEventTopic {

    MACHINE_REGISTRATION("machine"),
    MACHINE_CONNECTION("machine_connection"),
    NAYAX_POINT_OF_SALE("nayax_point_of_sale");

    private final String value;
    private EventServiceEventTopic(String value) {
        this.value = value.toLowerCase();
    }
    public String getValue() {
        return value;
    }

}
