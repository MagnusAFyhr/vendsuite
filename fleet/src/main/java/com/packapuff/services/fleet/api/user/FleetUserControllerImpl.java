package com.packapuff.services.fleet.api.user;

import com.packapuff.services.fleet.service.dto.machine.FleetMachineResponse;
import com.packapuff.services.fleet.service.dto.user.CreateFleetUserRequest;
import com.packapuff.services.fleet.service.dto.user.FleetUserResponse;
import com.packapuff.services.fleet.service.dto.user.GetFleetsUsersRequest;
import com.packapuff.services.fleet.service.user.FleetUserService;
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
public class FleetUserControllerImpl implements FleetUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleetUserControllerImpl.class);

    @Autowired
    private FleetUserService fleetUserService;

    /**
     *
     * Creates a fleet user and stores in DB
     *
     * @parameter RequestBody CreateFleetUserRequest
     *
     */
    public ResponseEntity<FleetUserResponse> createFleetUser(@Valid @RequestBody CreateFleetUserRequest createFleetUserRequest) {
        LOGGER.trace("Entering createFleetUser");

        FleetUserResponse fleetUser = fleetUserService.createFleetUser(createFleetUserRequest);

        LOGGER.trace("Exiting createFleetUser");
        return new ResponseEntity<>(fleetUser, HttpStatus.OK);
    }

    /**
     *
     * Gets list of users per fleet(s) from DB
     *
     * @parameter @RequestBody GetFleetsUsersRequest
     *
     */
    public ResponseEntity<List<FleetUserResponse>> getFleetsUsers(@Valid @RequestBody GetFleetsUsersRequest getFleetsUsersRequest) {
        LOGGER.trace("Entering getFleetUser");

        List<FleetUserResponse> fleetUsers = fleetUserService.getFleetsUsers(getFleetsUsersRequest);

        LOGGER.trace("Exiting getFleetUser");
        return new ResponseEntity<>(fleetUsers, HttpStatus.OK);
    }

    /**
     *
     * Delete fleet user from DB
     *
     * @parameter RequestBody List<UpdateFleetUserRequest>
     *
     */
    public ResponseEntity<FleetUserResponse> deleteFleetUser(Integer fleetId, Integer userId) {
        LOGGER.trace("Entering deleteFleetUser");

        FleetUserResponse fleetUser = fleetUserService.deleteFleetUser(fleetId, userId);

        LOGGER.trace("Exiting deleteFleetUser");
        return new ResponseEntity<>(fleetUser, HttpStatus.OK);
    }

    /**
     *
     * Delete all fleet machine from DB of specified fleet
     *
     * @param fleetId fleet identifier
     *
     */
    public ResponseEntity<List<FleetUserResponse>> deleteAllFleetUsersInFleet(Integer fleetId) {
        LOGGER.trace("Entering deleteAllFleetMachinesInFleet");

        List<FleetUserResponse> fleetUsers = fleetUserService.deleteAllFleetUsersInFleet(fleetId);

        LOGGER.trace("Exiting deleteAllFleetMachinesInFleet");
        return new ResponseEntity<>(fleetUsers, HttpStatus.OK);
    }

}
