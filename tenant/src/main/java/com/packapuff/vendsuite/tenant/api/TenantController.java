package com.packapuff.vendsuite.tenant.api;

import com.packapuff.vendsuite.tenant.service.TenantService;
import com.packapuff.vendsuite.tenant.service.dto.CreateTenantRequest;
import com.packapuff.vendsuite.tenant.service.dto.GetTenantsRequest;
import com.packapuff.vendsuite.tenant.service.dto.UpdateTenantRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class TenantController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TenantController.class);

    @Autowired
    private TenantService tenantService;

    /**
     *
     * Creates a tenant and stores in DB
     *
     * @param createTenantRequest request pojo
     *
     */
    public ResponseEntity<TenantResponse> createTenant(
            @Valid @RequestBody CreateTenantRequest createTenantRequest
    ) {
        LOGGER.trace("Entering TenantController::createTenant()");

        TenantResponse tenant = tenantService.createTenant(createTenantRequest);

        LOGGER.trace("Exiting TenantController::createTenant()");
        return new ResponseEntity<>(tenant, HttpStatus.OK);
    }

    /**
     *
     * Gets tenant(s) from DB by id
     *
     * @param getTenantsRequest request pojo
     *
     */
    public ResponseEntity<List<TenantResponse>> getTenants(GetTenantsRequest getTenantsRequest) {
        LOGGER.trace("Entering TenantController::getTenants()");

        List<TenantResponse> tenantResponses = tenantService.getTenants(getTenantsRequest);

        LOGGER.trace("Exiting TenantController::getTenants()");
        return new ResponseEntity<>(tenantResponses, HttpStatus.OK);
    }

    /**
     *
     * Updates tenant(s) in DB by id
     *
     * @param updateTenantRequests list of request pojo
     *
     */
    public ResponseEntity<List<TenantResponse>> updateTenants(List<UpdateTenantRequest> updateTenantRequests) {
        LOGGER.trace("Entering TenantController::updateTenants()");

        List<TenantResponse> tenantResponses = tenantService.updateTenants(updateTenantRequests);

        LOGGER.trace("Exiting TenantController::updateTenants()");
        return new ResponseEntity<>(tenantResponses, HttpStatus.OK);
    }

    /**
     *
     * Deletes a tenant in DB by id
     *
     */
    public ResponseEntity<TenantResponse> deleteTenant(Integer tenantId) {
        LOGGER.trace("Entering TenantController::deleteTenant()");

        TenantResponse tenantResponse = tenantService.deleteTenant(tenantId);

        LOGGER.trace("Exiting TenantController::deleteTenant()");
        return new ResponseEntity<>(tenantResponse, HttpStatus.OK);
    }
}
