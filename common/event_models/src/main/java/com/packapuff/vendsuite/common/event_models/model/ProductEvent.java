package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class ProductEvent extends BaseSystemEvent {

    public ProductEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
