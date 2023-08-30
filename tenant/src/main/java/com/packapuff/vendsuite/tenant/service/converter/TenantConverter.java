package com.packapuff.vendsuite.tenant.service.converter;


import com.packapuff.vendsuite.tenant.service.dto.CreateTenantRequest;
import com.packapuff.vendsuite.tenant.service.dto.TenantResponse;
import com.packapuff.vendsuite.tenant.service.entity.Tenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TenantConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantConverter.class);

    public Tenant convertToTenantEntity(CreateTenantRequest request) {
        LOGGER.trace("Entering TenantConverter::convertToTenantEntity()");

        Tenant tenant = new Tenant();

        tenant.setTenantName(request.getTenantName());

        tenant.setCreatedTimestamp(OffsetDateTime.now());
        tenant.setUpdatedTimestamp(OffsetDateTime.now());

        LOGGER.trace("Exiting TenantConverter::convertToTenantEntity()");
        return tenant;
    }

    public TenantResponse convertToTenantResponse(Tenant tenant) {
        LOGGER.trace("Entering TenantConverter::convertToTenantResponse()");

        TenantResponse response = new TenantResponse();

        response.setTenantId(tenant.getTenantId());
        response.setTenantName(tenant.getTenantName());

        response.setCreatedTimestamp(tenant.getCreatedTimestamp());
        response.setUpdatedTimestamp(tenant.getUpdatedTimestamp());

        LOGGER.trace("Exiting TenantConverter::convertToTenantResponse()");
        return response;
    }

    public List<TenantResponse> convertToTenantResponseList(List<Tenant> tenantList) {
        LOGGER.trace("Entering TenantConverter::convertToTenantResponseList()");

        List<TenantResponse> responseList = new ArrayList<>();

        for (Tenant tenant: tenantList) {
            responseList.add(convertToTenantResponse(tenant));
        }

        LOGGER.trace("Exiting TenantConverter::convertToTenantResponseList()");
        return responseList;
    }
}
