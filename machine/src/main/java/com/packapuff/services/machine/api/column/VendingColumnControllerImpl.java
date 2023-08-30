package com.packapuff.services.machine.api.column;

import com.packapuff.services.machine.service.column.VendingColumnService;
import com.packapuff.services.machine.service.dto.column.*;
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
public class VendingColumnControllerImpl implements VendingColumnController {

    private static final Logger log = LoggerFactory.getLogger(VendingColumnControllerImpl.class);

    @Autowired
    private VendingColumnService vendingColumnService;

    /**
     *
     * Adds a vending column and stores in DB
     *
     * @parameter RequestBody CreateVendingColumnRequest
     *
     */
    public ResponseEntity<List<VendingColumnResponse>> createVendingColumns(
            @Valid @RequestBody List<CreateVendingColumnRequest> createVendingColumnRequestList) {
        log.trace("Entering addVendingColumn");

        List<VendingColumnResponse> columnResponseList = vendingColumnService.createVendingColumns(createVendingColumnRequestList);

        log.trace("Exiting addVendingColumn");
        return new ResponseEntity<>(columnResponseList, HttpStatus.OK);
    }

    /**
     *
     * Get vending column columns(s) from DB
     *
     * @parameter RequestBody GetVendingColumnsRequest
     *
     */
    public ResponseEntity<List<VendingColumnResponse>> getVendingColumns(
            @Valid @RequestBody GetVendingColumnsRequest getVendingColumnsRequest) {
        log.trace("Entering getVendingColumns");

        List<VendingColumnResponse> columns = vendingColumnService.getVendingColumns(getVendingColumnsRequest);

        log.trace("Exiting getVendingColumns");
        return new ResponseEntity<>(columns, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VendingColumnResponse> getVendingColumnByMdbCode(Integer machineId, String mdbCode) {
        log.trace("Entering getVendingColumnByMdbCode");

        VendingColumnResponse vendingColumnResponse = vendingColumnService.getVendingColumnByMdbCode(machineId, mdbCode);

        log.trace("Exiting getVendingColumnByMdbCode");
        return new ResponseEntity<>(vendingColumnResponse, HttpStatus.OK);    }

    /**
     *
     * Filter vending column columns(s) from DB
     *
     * @parameter RequestBody ListMachinesRequest
     *
     */
    public ResponseEntity<List<VendingColumnResponse>> listVendingColumns(
            @Valid @RequestBody ListVendingColumnsRequest listVendingColumnsRequest) {
        log.trace("Entering listVendingColumns");

        List<VendingColumnResponse> columns = vendingColumnService.listVendingColumns(listVendingColumnsRequest);

        log.trace("Exiting listVendingColumns");
        return new ResponseEntity<>(columns, HttpStatus.OK);
    }

    /**
     *
     * Update machines in DB from list
     *
     * @parameter @RequestBody List<UpdateMachineRequest>
     *
     */
    public ResponseEntity<List<VendingColumnResponse>> updateVendingColumns(
            @Valid @RequestBody List<UpdateVendingColumnRequest> updateVendingColumnRequestList) {
        log.trace("Entering updateVendingColumns");

        List<VendingColumnResponse> updatedColumns = vendingColumnService.updateVendingColumns(updateVendingColumnRequestList);

        log.trace("Exiting updateVendingColumns");
        return new ResponseEntity<>(updatedColumns, HttpStatus.OK);
    }

    /**
     *
     * Delete vending column from DB
     *
     * @parameter Integer
     *
     */
    public ResponseEntity<List<VendingColumnResponse>> deleteVendingColumns(Integer machineId) {
        log.trace("Entering deleteMachine");

        List<VendingColumnResponse> deletedVendingColumns = vendingColumnService.deleteVendingColumns(machineId);

        log.trace("Exiting deleteMachine");
        return new ResponseEntity<>(deletedVendingColumns, HttpStatus.OK);
    }
    
    /**
     *
     * Delete vending column from DB
     *
     * @param machineId machine identifier
     * @param mdbCode column identifier
     *
     */
    public ResponseEntity<VendingColumnResponse> deleteVendingColumn(Integer machineId, String mdbCode) {
        log.trace("Entering deleteMachine");

        VendingColumnResponse deletedColumn = vendingColumnService.deleteVendingColumn(machineId, mdbCode);

        log.trace("Exiting deleteMachine");
        return new ResponseEntity<>(deletedColumn, HttpStatus.OK);
    }

}
