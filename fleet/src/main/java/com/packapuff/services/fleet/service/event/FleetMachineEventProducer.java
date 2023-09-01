package com.packapuff.services.fleet.service.event;

import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.FleetMachineEvent;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetMachineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class FleetMachineEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishFleetMachineCreatedEvent(final FleetMachineResponse fleetMachineResponse) {
        System.out.println("publishFleetMachineCreatedEvent");
        FleetMachineEvent fleetMachineCreatedEvent = new FleetMachineEvent(
                fleetMachineResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(fleetMachineCreatedEvent);
    }

    public void publishFleetMachineDeletedEvent(final FleetMachineResponse fleetMachineResponse) {
        System.out.println("publishFleetMachineDeletedEvent");
        FleetMachineEvent fleetMachineDeletedEvent = new FleetMachineEvent(
                fleetMachineResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(fleetMachineDeletedEvent);
    }

    public void publishFleetMachineUpdatedEvent(final FleetMachineResponse fleetMachineResponse) {
        System.out.println("publishFleetUpdatedCreatedEvent");
        FleetMachineEvent fleetMachineUpdatedEvent = new FleetMachineEvent(
                fleetMachineResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(fleetMachineUpdatedEvent);
    }
}
