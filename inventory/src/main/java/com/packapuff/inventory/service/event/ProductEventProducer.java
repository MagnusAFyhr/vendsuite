package com.packapuff.inventory.service.event;

import com.packapuff.inventory.service.dto.product.ProductResponse;
import com.packapuff.inventory.service.entity.Product;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class ProductEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishProductCreatedEvent(final ProductResponse productResponse) {
        System.out.println("publishProductCreatedEvent");
        ProductEvent productCreatedEvent = new ProductEvent(
                productResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(productCreatedEvent);
    }

    public void publishProductDeletedEvent(final ProductResponse productResponse) {
        System.out.println("publishProductCreatedEvent");
        ProductEvent productDeletedEvent = new ProductEvent(
                productResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(productDeletedEvent);
    }

    public void publishProductUpdatedEvent(final ProductResponse productResponse) {
        System.out.println("publishProductCreatedEvent");
        ProductEvent productUpdatedEvent = new ProductEvent(
                productResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(productUpdatedEvent);
    }
}
