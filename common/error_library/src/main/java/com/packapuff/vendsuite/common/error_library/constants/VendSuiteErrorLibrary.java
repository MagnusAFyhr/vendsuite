package com.packapuff.vendsuite.common.error_library.constants;

import com.packapuff.vendsuite.common.error_library.model.VendSuiteError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VendSuiteErrorLibrary implements IVendSuiteErrorProvider {

    /** A generic error occurred */
    VEND_SUITE_GENERIC_GENERIC(new VendSuiteError(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "VEND-SUITE-GEN-0001",
            "A generic error occurred in {serviceName}.")),
    VEND_SUITE_GENERIC_MISSING_HEADER(new VendSuiteError(
            HttpStatus.BAD_REQUEST,
            "VEND-SUITE-GEN-0002",
            "Missing required header {headerName} in request.")),
    VEND_SUITE_GENERIC_INVALID_INPUT(new VendSuiteError(
            HttpStatus.BAD_REQUEST,
            "VEND-SUITE-GEN-0003",
            "Invalid input value type used for {paramName} in request.")),
    VEND_SUITE_GENERIC_DB_SAVE_FAILED(new VendSuiteError(
            HttpStatus.BAD_REQUEST,
            "VEND-SUITE-GEN-0004",
            "Failed to save {entity} to database.")),
    VEND_SUITE_GENERIC_DUPLICATE_ENTITY(new VendSuiteError(
            HttpStatus.BAD_REQUEST,
            "VEND-SUITE-GEN-0005",
            "Entity '{entity_type}' already present in the database.")),
    VEND_SUITE_GENERIC_ENTITY_NOT_FOUND(new VendSuiteError(
            HttpStatus.BAD_REQUEST,
            "VEND-SUITE-GEN-0006",
            "Entity {entity_type} of uid {uid} not found in the database.")),
    VEND_SUITE_GENERIC_DB_CONNECTION(new VendSuiteError(
            HttpStatus.BAD_REQUEST,
            "VEND-SUITE-GEN-0007",
            "Failed to connect to the database.")),
    VEND_SUITE_GENERIC_DB(new VendSuiteError(
            HttpStatus.BAD_REQUEST,
            "VEND-SUITE-GEN-0008",
            "A generic error occurred in the database.")),
    VEND_SUITE_GENERIC_INVALID_INPUT_RANGE(new VendSuiteError(
            HttpStatus.BAD_REQUEST,
            "VEND-SUITE-GEN-0009",
            "Invalid input value is not within range; {minRange} < '{value}' < {maxRange}"));

    private final VendSuiteError vendSuiteError;

    @Override
    public VendSuiteError getError() {
        return vendSuiteError;
    }
}
