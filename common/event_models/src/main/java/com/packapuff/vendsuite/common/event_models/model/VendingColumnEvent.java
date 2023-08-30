package com.packapuff.vendsuite.common.event_models.model;

import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class VendingColumnEvent extends BaseSystemEvent {

    public VendingColumnEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}