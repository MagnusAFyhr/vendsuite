package com.packapuff.vendsuite.common.event_models.model;

public class PointOfSaleEvent extends BaseSystemEvent {

    public PointOfSaleEvent(Object source) {
        super(source, "sale");
    }
}
