package com.packapuff.inventory.service.event;

import com.packapuff.inventory.service.dto.product_map.ProductMapResponse;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.ProductMapEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class ProductMapEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishProductMapCreatedEvent(final ProductMapResponse productMapResponse) {
        System.out.println("publishProductMapCreatedEvent");
        ProductMapEvent productMapCreatedEvent = new ProductMapEvent(
                productMapResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(productMapCreatedEvent);
    }

    public void publishProductMapDeletedEvent(final ProductMapResponse productMapResponse) {
        System.out.println("publishProductMapCreatedEvent");
        ProductMapEvent productMapDeletedEvent = new ProductMapEvent(
                productMapResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(productMapDeletedEvent);
    }

    public void publishProductMapUpdatedEvent(final ProductMapResponse productMapResponse) {
        System.out.println("publishProductMapCreatedEvent");
        ProductMapEvent productMapUpdatedEvent = new ProductMapEvent(
                productMapResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(productMapUpdatedEvent);
    }
}
