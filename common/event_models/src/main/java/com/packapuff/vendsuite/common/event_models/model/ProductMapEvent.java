package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class ProductMapEvent extends BaseSystemEvent {

    public ProductMapEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
