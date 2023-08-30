package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class UserEvent extends BaseSystemEvent {
    public UserEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
