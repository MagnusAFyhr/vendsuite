package com.packapuff.services.fleet.service.event;

import com.packapuff.services.fleet.service.dto.user.FleetUserResponse;
import com.packapuff.services.fleet.service.user.FleetUserService;
import com.packapuff.services.user.service.dto.UserResponse;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.List;

public class UserEventConsumer implements ApplicationListener<UserEvent> {

    @Autowired
    FleetUserService fleetUserService;

    @Override
    public void onApplicationEvent(UserEvent event) {
        System.out.println("Received UserEvent from UserEventConsumer::onApplicationEvent");
        try {

            // DELETED event type
            if (event.getEventType().equals(CrudEventType.DELETED.getValue())) {

                UserResponse userResponse = (UserResponse) event.getSource();

                List<FleetUserResponse> fleetUserResponses =
                        fleetUserService.deleteUserFromAllFleets(userResponse.getUserId());

            }

        } catch(Exception e) {
            System.out.println("Received bad UserEvent");
        }
        System.out.println("Received UserEvent - " + event.getSource().toString());
    }

}
