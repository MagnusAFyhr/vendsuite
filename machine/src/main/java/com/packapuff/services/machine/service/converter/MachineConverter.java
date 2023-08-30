package com.packapuff.services.machine.service.converter;

import com.packapuff.services.machine.service.dto.CreateMachineRequest;
import com.packapuff.services.machine.service.dto.MachineResponse;
import com.packapuff.services.machine.service.entity.Machine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MachineConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineConverter.class);

    public Machine convertToMachineEntity(CreateMachineRequest request) {
        LOGGER.trace("Entering convertToMachineEntity");

        Machine machine = new Machine();

        machine.setTelemetryId(request.getTelemetryId());
        machine.setLockId(request.getLockId());
        machine.setMachineName(request.getMachineName());
        machine.setIsConnected(request.getIsConnected());
        machine.setWidth(request.getWidth());
        machine.setHeight(request.getHeight());
        machine.setOperationStartTime(request.getOperationStartTime());
        machine.setOperationEndTime(request.getOperationEndTime());

        machine.setCreatedTimestamp(OffsetDateTime.now());
        machine.setUpdatedTimestamp(OffsetDateTime.now());

        LOGGER.trace("Exiting convertToMachineEntity");
        return machine;
    }

    public MachineResponse convertToMachineResponse(Machine machine) {
        LOGGER.debug("Entering convertToMachineResponse");

        MachineResponse response = new MachineResponse();

        response.setMachineId(machine.getMachineId());
        response.setTelemetryId(machine.getTelemetryId());
        response.setLockId(machine.getLockId());
        response.setMachineName(machine.getMachineName());
        response.setIsConnected(machine.getIsConnected());
        response.setWidth(machine.getWidth());
        response.setHeight(machine.getHeight());
        response.setOperationStartTime(machine.getOperationStartTime());
        response.setOperationEndTime(machine.getOperationEndTime());
        response.setCreatedTimestamp(machine.getCreatedTimestamp());
        response.setUpdatedTimestamp(machine.getUpdatedTimestamp());

        LOGGER.debug("Exiting convertToMachineResponse");
        return response;
    }

    public List<MachineResponse> convertToMachineResponseList(List<Machine> userList) {
        LOGGER.trace("Entering convertToMachineResponseList");

        List<MachineResponse> responseList = new ArrayList<>();

        for (Machine user: userList) {
            responseList.add(convertToMachineResponse(user));
        }

        LOGGER.trace("Exiting convertToMachineResponseList");
        return responseList;
    }
}
