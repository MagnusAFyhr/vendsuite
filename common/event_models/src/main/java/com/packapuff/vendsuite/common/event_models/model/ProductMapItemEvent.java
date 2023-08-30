package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class ProductMapItemEvent extends BaseSystemEvent {

    public ProductMapItemEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
