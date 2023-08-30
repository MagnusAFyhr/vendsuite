package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class CategoryEvent extends BaseSystemEvent {

    public CategoryEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
