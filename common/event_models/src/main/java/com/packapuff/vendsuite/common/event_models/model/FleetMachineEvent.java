package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class FleetMachineEvent extends BaseSystemEvent {
    public FleetMachineEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
