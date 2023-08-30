package com.packapuff.services.fleet.api.user;

import com.packapuff.services.fleet.service.dto.machine.FleetMachineResponse;
import com.packapuff.services.fleet.service.dto.user.CreateFleetUserRequest;
import com.packapuff.services.fleet.service.dto.user.FleetUserResponse;
import com.packapuff.services.fleet.service.dto.user.GetFleetsUsersRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
@RequestMapping("/fleets/users")
public interface FleetUserController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<FleetUserResponse> createFleetUser(@Valid @RequestBody CreateFleetUserRequest requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<FleetUserResponse>> getFleetsUsers(@Valid @RequestBody GetFleetsUsersRequest requestBody);


    @DeleteMapping(value = "/{fleetId}/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<FleetUserResponse> deleteFleetUser(
            @Valid @PathVariable("fleetId") @NotNull Integer fleetId,
            @Valid @PathVariable("userId") @NotNull Integer userId);

    @DeleteMapping(value = "/{fleetId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<FleetUserResponse>> deleteAllFleetUsersInFleet(
            @Valid @PathVariable("fleetId") @NotNull Integer fleetId);

}
