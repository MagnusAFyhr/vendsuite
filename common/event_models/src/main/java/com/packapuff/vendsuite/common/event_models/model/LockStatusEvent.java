package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.LockEventType;

public class LockStatusEvent extends BaseSystemEvent {

    public LockStatusEvent(Object source, LockEventType eventType) {
        super(source, eventType.toString());
    }

}
