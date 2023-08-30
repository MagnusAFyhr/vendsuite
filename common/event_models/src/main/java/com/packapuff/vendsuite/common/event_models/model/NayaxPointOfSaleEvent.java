package com.packapuff.vendsuite.common.event_models.model;


import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;

public class NayaxPointOfSaleEvent extends BaseSystemEvent {

    public NayaxPointOfSaleEvent(Object source) {
        super(source, "sale");
    }
}
