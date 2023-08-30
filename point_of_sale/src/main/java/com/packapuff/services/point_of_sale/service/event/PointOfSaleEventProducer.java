package com.packapuff.services.point_of_sale.service.event;

import com.packapuff.services.point_of_sale.service.dto.PointOfSaleResponse;
import com.packapuff.vendsuite.common.event_models.model.PointOfSaleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class PointOfSaleEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishPointOfSaleCreatedEvent(final PointOfSaleResponse pointOfSaleResponse) {
        System.out.println("publishPointOfSaleCreatedEvent");
        PointOfSaleEvent pointOfSaleCreatedEvent = new PointOfSaleEvent(
                pointOfSaleResponse);
        applicationEventPublisher.publishEvent(pointOfSaleCreatedEvent);
    }

}
