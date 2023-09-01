package com.packapuff.services.fleet.service.converter;


import com.packapuff.services.fleet.service.dto.CreateFleetRequest;
import com.packapuff.services.fleet.service.entity.Fleet;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FleetConverter {

    private static final Logger log = LoggerFactory.getLogger(FleetConverter.class);

    public Fleet convertToFleetEntity(CreateFleetRequest request) {
        log.trace("Entering convertToFleetEntity");

        Fleet fleet = new Fleet();

        fleet.setWarehouseId(request.getWarehouseId());
        fleet.setRegionSiteId(request.getRegionSiteId());
        fleet.setFleetName(request.getFleetName());
        fleet.setDescription(request.getDescription());

        fleet.setCreatedTimestamp(OffsetDateTime.now());
        fleet.setUpdatedTimestamp(OffsetDateTime.now());

        log.trace("Exiting convertToFleetEntity");
        return fleet;
    }

    public FleetResponse convertToFleetResponse(Fleet fleet) {
        log.debug("Entering convertToFleetResponse");

        FleetResponse response = new FleetResponse();

        response.setFleetId(fleet.getFleetId());
        response.setWarehouseId(fleet.getWarehouseId());
        response.setRegionSiteId(fleet.getRegionSiteId());
        response.setCreatorUserId(fleet.getCreatorUserId());
        response.setFleetName(fleet.getFleetName());
        response.setDescription(fleet.getDescription());

        response.setCreatedTimestamp(fleet.getCreatedTimestamp());
        response.setUpdatedTimestamp(fleet.getUpdatedTimestamp());

        log.debug("Exiting convertToFleetResponse");
        return response;
    }

    public List<FleetResponse> convertToFleetResponseList(List<Fleet> fleetList) {
        log.trace("Entering convertToFleetResponseList");

        List<FleetResponse> responseList = new ArrayList<>();

        for (Fleet fleet: fleetList) {
            responseList.add(convertToFleetResponse(fleet));
        }

        log.trace("Exiting convertToFleetResponseList");
        return responseList;
    }
}
