package com.packapuff.services.user.service.event;

import com.packapuff.services.user.service.dto.UserResponse;
import com.packapuff.services.user.service.entity.User;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class UserEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishUserCreatedEvent(final UserResponse user) {
        System.out.println("publishUserCreatedEvent");
        UserEvent userCreatedEvent = new UserEvent(
                user, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(userCreatedEvent);
    }

    public void publishUserDeletedEvent(final UserResponse user) {
        System.out.println("publishUserCreatedEvent");
        UserEvent userDeletedEvent = new UserEvent(
                user, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(userDeletedEvent);
    }

    public void publishUserUpdatedEvent(final UserResponse user) {
        System.out.println("publishUserCreatedEvent");
        UserEvent userUpdatedEvent = new UserEvent(
                user, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(userUpdatedEvent);
    }
}
