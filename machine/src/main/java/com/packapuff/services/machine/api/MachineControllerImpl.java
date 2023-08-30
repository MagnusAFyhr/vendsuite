package com.packapuff.services.machine.api;

import com.packapuff.services.machine.api.MachineController;
import com.packapuff.services.machine.service.MachineService;
import com.packapuff.services.machine.service.dto.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * [Insert Comments]
 *
 */
@RestController
public class MachineControllerImpl implements MachineController {

    private static final Logger log = LoggerFactory.getLogger(MachineControllerImpl.class);

    @Autowired
    private MachineService machineService;

    /**
     *
     * Creates a user and stores in DB
     *
     * @parameter RequestBody CreateUserRequest
     *
     */
    public ResponseEntity<MachineResponse> createMachine(@Valid @RequestBody CreateMachineRequest createMachineRequest) {
        log.trace("Entering createMachine");

        MachineResponse machineResponse = machineService.createMachine(createMachineRequest);

        log.trace("Exiting createMachine");
        return new ResponseEntity<>(machineResponse, HttpStatus.OK);
    }

    /**
     *
     * Checks if machine exists in DB
     *
     * @parameter Integer machineId
     *
     */
    public ResponseEntity<Boolean> getMachineExists(Integer machineId) {
        // TODO: complete implementation
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    /**
     *
     * Gets machines from DB
     *
     * @parameter Integer machineId
     *
     */
    public ResponseEntity<List<MachineResponse>> getMachines(@Valid @RequestBody GetMachinesRequest getMachinesRequest) {
        log.trace("Entering getMachines");

        List<MachineResponse> machines = machineService.getMachines(getMachinesRequest);

        log.trace("Exiting getMachines");
        return new ResponseEntity<>(machines, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MachineResponse> getMachineByTelemetryId(Integer telemetryId) {
        log.trace("Entering getMachineByTelemetryId");

        MachineResponse machine = machineService.getMachineByTelemetryId(telemetryId);

        log.trace("Exiting getMachineByTelemetryId");
        return new ResponseEntity<>(machine, HttpStatus.OK);
    }

    /**
     *
     * Gets machines from DB as list
     *
     * @parameter RequestBody ListMachinesRequest
     *
     */
    public ResponseEntity<List<MachineResponse>> listMachines(@Valid @RequestBody ListMachinesRequest listMachinesRequest) {
        log.trace("Entering listMachines");

        List<MachineResponse> machines = machineService.listMachines(listMachinesRequest);

        log.trace("Exiting listMachines");
        return new ResponseEntity<>(machines, HttpStatus.OK);
    }

    /**
     *
     * Update machines in DB from list
     *
     * @parameter @RequestBody List<UpdateMachineRequest>
     *
     */
    public ResponseEntity<List<MachineResponse>> updateMachines(@Valid @RequestBody List<UpdateMachineRequest> requestBody) {
        log.trace("Entering updateMachines");

        List<MachineResponse> machineResponses = machineService.updateMachines(requestBody);

        log.trace("Exiting updateMachines");
        return new ResponseEntity<>(machineResponses, HttpStatus.OK);
    }

    /**
     *
     * Delete machine from DB
     *
     * @parameter Integer
     *
     */
    public ResponseEntity<MachineResponse> deleteMachine(Integer machineId) {
        log.trace("Entering deleteMachine");

        MachineResponse machineResponse = machineService.deleteMachine(machineId);

        log.trace("Exiting deleteMachine");
        return new ResponseEntity<>(machineResponse, HttpStatus.OK);
    }

}
