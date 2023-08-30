package com.packapuff.services.events.service.producer;

import com.packapuff.vendsuite.common.event_models.enums.ConnectionEventType;
import com.packapuff.vendsuite.common.event_models.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class EventProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public MachineRegistrationEvent publishMachineRegistrationEvent(Object resource) {
        LOGGER.trace("Entering EventProducer::publishMachineRegistrationEvent()");

        MachineRegistrationEvent machineRegistrationEvent = new MachineRegistrationEvent(resource);
        eventPublisher.publishEvent(machineRegistrationEvent);

        LOGGER.trace("Exiting EventProducer::publishMachineRegistrationEvent()");
        return machineRegistrationEvent;
    }
    public MachineConnectionEvent publishMachineConnectionEvent(Object resource, ConnectionEventType eventType) {
        LOGGER.trace("Entering EventProducer::publishMachineConnectionEvent()");

        MachineConnectionEvent machineConnectionEvent = new MachineConnectionEvent(resource, eventType);
        eventPublisher.publishEvent(machineConnectionEvent);

        LOGGER.trace("Exiting EventProducer::publishMachineConnectionEvent()");
        return machineConnectionEvent;
    }

    public PointOfSaleEvent publishNayaxPointOfSaleEvent(Object resource) {
        LOGGER.trace("Entering EventProducer::publishPointOfSaleEvent()");

        PointOfSaleEvent pointOfSaleEvent = new PointOfSaleEvent(resource);
        eventPublisher.publishEvent(pointOfSaleEvent);

        LOGGER.trace("Exiting EventProducer::publishPointOfSaleEvent()");
        return pointOfSaleEvent;
    }
}
