package com.packapuff.services.machine.api;

import com.packapuff.services.machine.service.dto.*;
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
@RequestMapping("/machines")
public interface MachineController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<MachineResponse> createMachine(@Valid @RequestBody CreateMachineRequest requestBody);

    @GetMapping(value = "/machine/{machineId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<Boolean> getMachineExists(@Valid @PathVariable("machineId") @NotNull Integer machineId);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<MachineResponse>> getMachines(@Valid @RequestBody GetMachinesRequest requestBody);

    @GetMapping(value = "/telemetryId/{telemetryId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<MachineResponse> getMachineByTelemetryId(@Valid @PathVariable("telemetryId") @NotNull Integer telemetryId);

    @GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<MachineResponse>> listMachines(@Valid @RequestBody ListMachinesRequest requestBody);

    @PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<MachineResponse>> updateMachines(@Valid @RequestBody List<UpdateMachineRequest> requestBody);

    @DeleteMapping(value = "/{machineId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<MachineResponse> deleteMachine(@Valid @PathVariable("machineId") @NotNull Integer machineId);

}
