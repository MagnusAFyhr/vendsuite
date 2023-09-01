package com.packapuff.services.fleet.service.converter;

import com.packapuff.services.fleet.service.dto.user.CreateFleetUserRequest;
import com.packapuff.services.fleet.service.entity.FleetUser;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FleetUserConverter {

    private static final Logger log = LoggerFactory.getLogger(FleetUserConverter.class);

    public FleetUser convertToFleetUserEntity(CreateFleetUserRequest request) {
        log.trace("Entering convertToFleetUserEntity");

        FleetUser fleetUser = new FleetUser();

        fleetUser.setFleetId(request.getFleetId());
        fleetUser.setUserId(request.getUserId());
        fleetUser.setFleetRole(request.getFleetRole());

        fleetUser.setCreatedTimestamp(OffsetDateTime.now());
        fleetUser.setUpdatedTimestamp(OffsetDateTime.now());

        log.trace("Exiting convertToFleetUserEntity");
        return fleetUser;
    }

    public FleetUserResponse convertToFleetUserResponse(FleetUser fleetUser) {
        log.debug("Entering convertToFleetUserResponse");

        FleetUserResponse response = new FleetUserResponse();

        response.setFleetUserAssocId(fleetUser.getFleetUserAssocId());
        response.setFleetId(fleetUser.getFleetId());
        response.setUserId(fleetUser.getUserId());
        response.setFleetRole(fleetUser.getFleetRole());

        response.setCreatedTimestamp(fleetUser.getCreatedTimestamp());
        response.setUpdatedTimestamp(fleetUser.getUpdatedTimestamp());

        log.debug("Exiting convertToFleetUserResponse");
        return response;
    }

    public List<FleetUserResponse> convertToFleetUserResponseList(List<FleetUser> fleetUserList) {
        log.trace("Entering convertToFleetUserResponseList");

        List<FleetUserResponse> responseList = new ArrayList<>();

        for (FleetUser fleetUser: fleetUserList) {
            responseList.add(convertToFleetUserResponse(fleetUser));
        }

        log.trace("Exiting convertToFleetUserResponseList");
        return responseList;
    }
}
