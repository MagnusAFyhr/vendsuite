package com.packapuff.services.machine.service.event;

import com.packapuff.services.machine.service.dto.column.VendingColumnResponse;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.VendingColumnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class VendingColumnEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishVendingColumnCreatedEvent(final VendingColumnResponse vendingColumnResponse) {
        System.out.println("publishVendingColumnCreatedEvent");
        VendingColumnEvent vendingColumnCreatedEvent = new VendingColumnEvent(
                vendingColumnResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(vendingColumnCreatedEvent);
    }

    public void publishVendingColumnDeletedEvent(final VendingColumnResponse vendingColumnResponse) {
        System.out.println("publishVendingColumnDeletedEvent");
        VendingColumnEvent vendingColumnDeletedEvent = new VendingColumnEvent(
                vendingColumnResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(vendingColumnDeletedEvent);
    }

    public void publishVendingColumnUpdatedEvent(final VendingColumnResponse vendingColumnResponse) {
        System.out.println("publishVendingColumnUpdatedEvent");
        VendingColumnEvent vendingColumnUpdatedEvent = new VendingColumnEvent(
                vendingColumnResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(vendingColumnUpdatedEvent);
    }

}
