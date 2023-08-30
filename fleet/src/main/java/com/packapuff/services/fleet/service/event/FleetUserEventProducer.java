package com.packapuff.services.fleet.service.event;

import com.packapuff.services.fleet.service.dto.user.FleetUserResponse;
import com.packapuff.services.fleet.service.entity.FleetUser;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.FleetUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class FleetUserEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishFleetUserCreatedEvent(final FleetUserResponse fleetUserResponse) {
        System.out.println("publishFleetUserCreatedEvent");
        FleetUserEvent fleetUserCreatedEvent = new FleetUserEvent(
                fleetUserResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(fleetUserCreatedEvent);
    }

    public void publishFleetUserDeletedEvent(final FleetUserResponse fleetUserResponse) {
        System.out.println("publishFleetUserDeletedEvent");
        FleetUserEvent fleetUserDeletedEvent = new FleetUserEvent(
                fleetUserResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(fleetUserDeletedEvent);
    }

    public void publishFleetUserUpdatedEvent(final FleetUserResponse fleetUserResponse) {
        System.out.println("publishFleetUpdatedCreatedEvent");
        FleetUserEvent fleetUserUpdatedEvent = new FleetUserEvent(
                fleetUserResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(fleetUserUpdatedEvent);
    }
}
