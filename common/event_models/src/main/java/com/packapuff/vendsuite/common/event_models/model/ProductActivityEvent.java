package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class ProductActivityEvent extends BaseSystemEvent {

    public ProductActivityEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
