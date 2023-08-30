package com.packapuff.vendsuite.common.event_models.model;

import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class TenantEvent extends BaseSystemEvent {
    public TenantEvent(Object source, CrudEventType eventType) {
        super(source, eventType.toString());
    }
}
