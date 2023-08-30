package com.packapuff.inventory.service.event;

import com.packapuff.inventory.service.dto.product_activity.ProductActivityResponse;
import com.packapuff.inventory.service.entity.ProductActivity;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.ProductActivityEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class ProductActivityEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishProductActivityCreatedEvent(final ProductActivityResponse productActivityResponse) {
        System.out.println("publishProductActivityCreatedEvent");
        ProductActivityEvent productActivityCreatedEvent = new ProductActivityEvent(
                productActivityResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(productActivityCreatedEvent);
    }

    public void publishProductActivityDeletedEvent(final ProductActivityResponse productActivityResponse) {
        System.out.println("publishProductActivityCreatedEvent");
        ProductActivityEvent productActivityDeletedEvent = new ProductActivityEvent(
                productActivityResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(productActivityDeletedEvent);
    }

    public void publishProductActivityUpdatedEvent(final ProductActivityResponse productActivityResponse) {
        System.out.println("publishProductActivityCreatedEvent");
        ProductActivityEvent productActivityUpdatedEvent = new ProductActivityEvent(
                productActivityResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(productActivityUpdatedEvent);
    }
}
