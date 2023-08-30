package com.packapuff.vendsuite.common.event_models.model;

import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class SiteEvent extends BaseSystemEvent {
    public SiteEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
