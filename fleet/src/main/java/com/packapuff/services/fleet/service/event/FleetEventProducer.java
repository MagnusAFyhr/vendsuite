package com.packapuff.services.fleet.service.event;

import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.FleetEvent;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class FleetEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishFleetCreatedEvent(final FleetResponse fleetResponse) {
        System.out.println("publishFleetCreatedEvent");
        FleetEvent fleetCreatedEvent = new FleetEvent(
                fleetResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(fleetCreatedEvent);
    }

    public void publishFleetDeletedEvent(final FleetResponse fleetResponse) {
        System.out.println("publishFleetCreatedEvent");
        FleetEvent fleetDeletedEvent = new FleetEvent(
                fleetResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(fleetDeletedEvent);
    }

    public void publishFleetUpdatedEvent(final FleetResponse fleetResponse) {
        System.out.println("publishFleetCreatedEvent");
        FleetEvent fleetUpdatedEvent = new FleetEvent(
                fleetResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(fleetUpdatedEvent);
    }
}
