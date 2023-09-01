package com.packapuff.services.events.service;


import com.packapuff.services.events.service.enums.EventServiceEventTopic;
import com.packapuff.vendsuite.common.event_models.enums.ConnectionEventType;
import com.packapuff.vendsuite.common.event_models.model.BaseSystemEvent;
import com.packapuff.services.events.service.producer.EventProducer;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary.VEND_SUITE_GENERIC_GENERIC;
import static com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary.VEND_SUITE_GENERIC_INVALID_INPUT;

@Service
public class EventsServiceImpl implements EventsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventsServiceImpl.class);

    @Autowired
    private EventProducer eventProducer;

    @Override
    public BaseSystemEvent publishEvent(
            Object resource,
            EventServiceEventTopic eventCategory,
            String eventType
    ) {
        LOGGER.trace("Entering EventsServiceImpl::publishEvent()");

        BaseSystemEvent event = null;
        try {
            switch (eventCategory) {
                case MACHINE_REGISTRATION -> {
                    event = (BaseSystemEvent) eventProducer.publishMachineRegistrationEvent(resource);
                }
                case MACHINE_CONNECTION -> {
                    event = (BaseSystemEvent) eventProducer.publishMachineConnectionEvent(resource, ConnectionEventType.valueOf(eventType));
                }
                case NAYAX_POINT_OF_SALE -> {
                    event = (BaseSystemEvent) eventProducer.publishNayaxPointOfSaleEvent(resource);
                }
                default -> {
                    // throw correct exception
                    throw new VendSuiteException(VEND_SUITE_GENERIC_INVALID_INPUT.getVendSuiteError());
                }
            }
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        if (event == null) {
            // throw correct exception
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        LOGGER.trace("Exiting EventsServiceImpl::publishEvent()");
        return event;
    }

}
