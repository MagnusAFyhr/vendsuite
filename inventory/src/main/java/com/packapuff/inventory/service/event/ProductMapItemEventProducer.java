package com.packapuff.inventory.service.event;

import com.packapuff.inventory.service.dto.product_map_item.ProductMapItemResponse;
import com.packapuff.inventory.service.entity.ProductMapItem;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.ProductMapItemEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class ProductMapItemEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishProductMapItemCreatedEvent(final ProductMapItemResponse productMapItemResponse) {
        System.out.println("publishProductMapItemCreatedEvent");
        ProductMapItemEvent productMapItemCreatedEvent = new ProductMapItemEvent(
                productMapItemResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(productMapItemCreatedEvent);
    }

    public void publishProductMapItemDeletedEvent(final ProductMapItemResponse productMapItemResponse) {
        System.out.println("publishProductMapItemCreatedEvent");
        ProductMapItemEvent productMapItemDeletedEvent = new ProductMapItemEvent(
                productMapItemResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(productMapItemDeletedEvent);
    }

    public void publishProductMapItemUpdatedEvent(final ProductMapItemResponse productMapItemResponse) {
        System.out.println("publishProductMapItemCreatedEvent");
        ProductMapItemEvent productMapItemUpdatedEvent = new ProductMapItemEvent(
                productMapItemResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(productMapItemUpdatedEvent);
    }
}
