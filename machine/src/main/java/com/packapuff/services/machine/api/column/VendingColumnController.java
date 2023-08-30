package com.packapuff.services.machine.api.column;

import com.packapuff.services.machine.service.dto.column.*;
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
public interface VendingColumnController {

    @PostMapping(value = "/columns", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<VendingColumnResponse>> createVendingColumns(@Valid @RequestBody List<CreateVendingColumnRequest> requestBody);

    @GetMapping(value = "/columns", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<VendingColumnResponse>> getVendingColumns(@Valid @RequestBody GetVendingColumnsRequest requestBody);

    @GetMapping(value = "/{machineId}/columns/{mdbCode}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<VendingColumnResponse> getVendingColumnByMdbCode(
            @Valid @PathVariable("machineId") @NotNull Integer machineId,
            @Valid @PathVariable("mdbCode") @NotNull String mdbCode);

    @GetMapping(value = "/columns/list", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<VendingColumnResponse>> listVendingColumns(@Valid @RequestBody ListVendingColumnsRequest requestBody);

    @PatchMapping(value = "/columns", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<VendingColumnResponse>> updateVendingColumns(@Valid @RequestBody List<UpdateVendingColumnRequest> requestBody);

    @DeleteMapping(value = "/{machineId}/columns", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<VendingColumnResponse>> deleteVendingColumns(
            @Valid @PathVariable("machineId") @NotNull Integer machineId);
    @DeleteMapping(value = "/{machineId}/columns/{mdbCode}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<VendingColumnResponse> deleteVendingColumn(
            @Valid @PathVariable("machineId") @NotNull Integer machineId,
            @Valid @PathVariable("mdbCode") @NotNull String mdbCode);

}
