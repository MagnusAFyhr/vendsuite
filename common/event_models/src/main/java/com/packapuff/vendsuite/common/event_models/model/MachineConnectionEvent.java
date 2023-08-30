package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.ConnectionEventType;

public class MachineConnectionEvent extends BaseSystemEvent {

    public MachineConnectionEvent(Object source, ConnectionEventType eventType) {
        super(source, eventType.toString());
    }

}
