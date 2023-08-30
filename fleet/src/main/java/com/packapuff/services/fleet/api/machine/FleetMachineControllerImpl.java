package com.packapuff.services.fleet.api.machine;

import com.packapuff.services.fleet.service.dto.machine.CreateFleetMachineRequest;
import com.packapuff.services.fleet.service.dto.machine.FleetMachineResponse;
import com.packapuff.services.fleet.service.dto.machine.GetFleetsMachinesRequest;
import com.packapuff.services.fleet.service.machine.FleetMachineService;
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
public class FleetMachineControllerImpl implements FleetMachineController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleetMachineControllerImpl.class);

    @Autowired
    private FleetMachineService fleetMachineService;

    /**
     *
     * Creates a fleet machine and stores in DB
     *
     * @parameter RequestBody CreateFleetMachineRequest
     *
     */
    public ResponseEntity<FleetMachineResponse> createFleetMachine(@Valid @RequestBody CreateFleetMachineRequest createFleetMachineRequest) {
        LOGGER.trace("Entering createFleetMachine");

        FleetMachineResponse fleetMachine = fleetMachineService.createFleetMachine(createFleetMachineRequest);

        LOGGER.trace("Exiting createFleetMachine");
        return new ResponseEntity<>(fleetMachine, HttpStatus.OK);
    }

    /**
     *
     * Gets list of machines per fleet(s) from DB
     *
     * @param getFleetsMachinesRequest request pojo
     *
     */
    public ResponseEntity<List<FleetMachineResponse>> getFleetsMachines(@Valid @RequestBody GetFleetsMachinesRequest getFleetsMachinesRequest) {
        LOGGER.trace("Entering getFleetMachine");

        List<FleetMachineResponse> fleetMachines = fleetMachineService.getFleetsMachines(getFleetsMachinesRequest);

        LOGGER.trace("Exiting getFleetMachine");
        return new ResponseEntity<>(fleetMachines, HttpStatus.OK);
    }

    /**
     *
     * Delete fleet machine from DB
     *
     * @param machineId fleet machine identifier
     *
     */
    public ResponseEntity<FleetMachineResponse> deleteFleetMachine(Integer machineId) {
        LOGGER.trace("Entering deleteFleetMachine");

        FleetMachineResponse fleetMachine = fleetMachineService.deleteFleetMachine(machineId);

        LOGGER.trace("Exiting deleteFleetMachine");
        return new ResponseEntity<>(fleetMachine, HttpStatus.OK);
    }

    /**
     *
     * Delete all fleet machine from DB of specified fleet
     *
     * @param fleetId fleet identifier
     *
     */
    public ResponseEntity<List<FleetMachineResponse>> deleteAllFleetMachinesInFleet(Integer fleetId) {
        LOGGER.trace("Entering deleteAllFleetMachinesInFleet");

        List<FleetMachineResponse> fleetMachines = fleetMachineService.deleteAllFleetMachinesInFleet(fleetId);

        LOGGER.trace("Exiting deleteAllFleetMachinesInFleet");
        return new ResponseEntity<>(fleetMachines, HttpStatus.OK);
    }


}
