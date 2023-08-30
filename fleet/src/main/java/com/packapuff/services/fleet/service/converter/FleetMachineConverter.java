package com.packapuff.services.fleet.service.converter;

import com.packapuff.services.fleet.service.dto.machine.CreateFleetMachineRequest;
import com.packapuff.services.fleet.service.dto.machine.FleetMachineResponse;
import com.packapuff.services.fleet.service.entity.FleetMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FleetMachineConverter {

    private static final Logger log = LoggerFactory.getLogger(FleetMachineConverter.class);

    public FleetMachine convertToFleetMachineEntity(CreateFleetMachineRequest request) {
        log.trace("Entering convertToFleetMachineEntity");

        FleetMachine fleetMachine = new FleetMachine();

        fleetMachine.setFleetId(request.getFleetId());
        fleetMachine.setMachineId(request.getMachineId());

        fleetMachine.setCreatedTimestamp(OffsetDateTime.now());
        fleetMachine.setUpdatedTimestamp(OffsetDateTime.now());

        log.trace("Exiting convertToFleetMachineEntity");
        return fleetMachine;
    }

    public FleetMachineResponse convertToFleetMachineResponse(FleetMachine fleetMachine) {
        log.debug("Entering convertToFleetMachineResponse");

        FleetMachineResponse response = new FleetMachineResponse();

        response.setFleetMachineAssocId(fleetMachine.getFleetMachineAssocId());
        response.setFleetId(fleetMachine.getFleetId());
        response.setMachineId(fleetMachine.getMachineId());

        response.setCreatedTimestamp(fleetMachine.getCreatedTimestamp());
        response.setUpdatedTimestamp(fleetMachine.getUpdatedTimestamp());

        log.debug("Exiting convertToFleetMachineResponse");
        return response;
    }

    public List<FleetMachineResponse> convertToFleetMachineResponseList(List<FleetMachine> fleetMachineList) {
        log.trace("Entering convertToFleetMachineResponseList");

        List<FleetMachineResponse> responseList = new ArrayList<>();

        for (FleetMachine fleetMachine: fleetMachineList) {
            responseList.add(convertToFleetMachineResponse(fleetMachine));
        }

        log.trace("Exiting convertToFleetMachineResponseList");
        return responseList;
    }
}
