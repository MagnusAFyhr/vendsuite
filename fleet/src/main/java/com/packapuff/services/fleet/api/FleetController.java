package com.packapuff.services.fleet.api;

import com.packapuff.services.fleet.service.dto.*;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetResponse;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * [Insert Comments]
 *
 */

@Validated
@RequestMapping("/fleets")
public interface FleetController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<FleetResponse> createFleet(@Valid @RequestBody CreateFleetRequest requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<FleetResponse>> getFleets(@Valid @RequestBody GetFleetsRequest requestBody);

    @PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<FleetResponse>> updateFleets(@Valid @RequestBody List<UpdateFleetRequest> requestBody);

    @DeleteMapping(value = "/{fleetId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<FleetResponse> deleteFleet(@Valid @PathVariable("fleetId") @NotNull Integer fleetId);

}
