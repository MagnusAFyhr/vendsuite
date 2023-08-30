package com.packapuff.services.events.service;


import com.packapuff.services.events.service.enums.EventServiceEventTopic;
import com.packapuff.vendsuite.common.event_models.model.BaseSystemEvent;

public interface EventsService {

    BaseSystemEvent publishEvent(
            Object resource,
            EventServiceEventTopic eventTopic,
            String eventType);

}
