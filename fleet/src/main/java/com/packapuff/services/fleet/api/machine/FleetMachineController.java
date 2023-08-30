package com.packapuff.services.fleet.api.machine;

import com.packapuff.services.fleet.service.dto.machine.CreateFleetMachineRequest;
import com.packapuff.services.fleet.service.dto.machine.FleetMachineResponse;
import com.packapuff.services.fleet.service.dto.machine.GetFleetsMachinesRequest;
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
@RequestMapping("/fleets/machines")
public interface FleetMachineController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<FleetMachineResponse> createFleetMachine(@Valid @RequestBody CreateFleetMachineRequest requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<FleetMachineResponse>> getFleetsMachines(@Valid @RequestBody GetFleetsMachinesRequest requestBody);

    @DeleteMapping(value = "/machine/{machineId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<FleetMachineResponse> deleteFleetMachine(
            @Valid @PathVariable("machineId") @NotNull Integer machineId);

    @DeleteMapping(value = "/fleet/{fleetId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<FleetMachineResponse>> deleteAllFleetMachinesInFleet(
            @Valid @PathVariable("fleetId") @NotNull Integer fleetId);
}
