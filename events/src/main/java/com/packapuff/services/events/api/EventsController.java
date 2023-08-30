package com.packapuff.services.events.api;


import com.packapuff.services.events.service.enums.EventServiceEventTopic;
import com.packapuff.vendsuite.common.event_models.model.BaseSystemEvent;
import com.packapuff.services.events.service.EventsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsController.class);

    @Autowired
    EventsService eventsService;

    @PostMapping
    ResponseEntity<BaseSystemEvent> publishEvent(Object resource,
                                                 EventServiceEventTopic eventTopic,
                                                 String eventType) {
        LOGGER.trace("Entering EventsController::publishEvent()");

        BaseSystemEvent event = eventsService.publishEvent(resource, eventTopic, eventType);

        LOGGER.trace("Exiting EventsController::publishEvent()");
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
