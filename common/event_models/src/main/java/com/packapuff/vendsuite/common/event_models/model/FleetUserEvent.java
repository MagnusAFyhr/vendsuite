package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class FleetUserEvent extends BaseSystemEvent {
    public FleetUserEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
