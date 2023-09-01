package com.packapuff.services.fleet.api;

import com.packapuff.services.fleet.service.FleetService;
import com.packapuff.services.fleet.service.dto.*;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetResponse;
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
public class FleetControllerImpl implements FleetController {

    private static final Logger log = LoggerFactory.getLogger(FleetControllerImpl.class);

    @Autowired
    private FleetService fleetService;

    /**
     *
     * Creates a fleet and stores in DB
     *
     * @parameter RequestBody CreateFleetRequest
     *
     */
    public ResponseEntity<FleetResponse> createFleet(@Valid @RequestBody CreateFleetRequest createFleetRequest) {
        log.trace("Entering createFleet");

        FleetResponse fleet = fleetService.createFleet(createFleetRequest);

        log.trace("Exiting createFleet");
        return new ResponseEntity<>(fleet, HttpStatus.OK);
    }

    /**
     *
     * Gets a fleet from DB
     *
     * @parameter Integer fleetId
     *
     */
    public ResponseEntity<List<FleetResponse>> getFleets(@Valid @RequestBody GetFleetsRequest getFleetsRequest) {
        log.trace("Entering getFleets");

        List<FleetResponse> fleets = fleetService.getFleets(getFleetsRequest);

        log.trace("Exiting getFleets");
        return new ResponseEntity<>(fleets, HttpStatus.OK);
    }

    /**
     *
     * Update fleets in DB from list
     *
     * @parameter RequestBody List<UpdateFleetRequest>
     *
     */
    public ResponseEntity<List<FleetResponse>> updateFleets(@Valid @RequestBody List<UpdateFleetRequest> requestBody) {
        log.trace("Entering updateFleets");

        List<FleetResponse> fleets = fleetService.updateFleets(requestBody);

        log.trace("Exiting updateFleets");
        return new ResponseEntity<>(fleets, HttpStatus.OK);
    }

    /**
     *
     * Delete fleet from DB
     *
     * @parameter fleetId integer uid
     *
     */
    public ResponseEntity<FleetResponse> deleteFleet(Integer fleetId) {
        log.trace("Entering deleteFleet");

        FleetResponse fleet = fleetService.deleteFleet(fleetId);

        log.trace("Exiting deleteFleet");
        return new ResponseEntity<>(fleet, HttpStatus.OK);
    }

}
