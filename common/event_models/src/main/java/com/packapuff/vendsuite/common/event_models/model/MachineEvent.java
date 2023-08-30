package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class MachineEvent extends BaseSystemEvent {

    public MachineEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
