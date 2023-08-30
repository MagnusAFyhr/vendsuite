package com.packapuff.vendsuite.common.event_models.model;

import org.springframework.context.ApplicationEvent;

public class BaseSystemEvent extends ApplicationEvent {
    private final String eventType;

    public BaseSystemEvent(Object source, String eventType) {
        super(source);
        this.eventType = eventType;
    }
    public String getEventType() {
        return eventType;
    }
}
