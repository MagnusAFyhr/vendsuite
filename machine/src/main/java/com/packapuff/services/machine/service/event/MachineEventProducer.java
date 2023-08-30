package com.packapuff.services.machine.service.event;

import com.packapuff.services.machine.service.dto.MachineResponse;
import com.packapuff.services.machine.service.entity.Machine;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.MachineEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MachineEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishMachineCreatedEvent(final MachineResponse machineResponse) {
        System.out.println("publishMachineCreatedEvent");
        MachineEvent machineCreatedEvent = new MachineEvent(
                machineResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(machineCreatedEvent);
    }

    public void publishMachineDeletedEvent(final MachineResponse machineResponse) {
        System.out.println("publishMachineDeletedEvent");
        MachineEvent machineDeletedEvent = new MachineEvent(
                machineResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(machineDeletedEvent);
    }

    public void publishMachineUpdatedEvent(final MachineResponse machineResponse) {
        System.out.println("publishMachineUpdatedEvent");
        MachineEvent machineUpdatedEvent = new MachineEvent(
                machineResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(machineUpdatedEvent);
    }

}
