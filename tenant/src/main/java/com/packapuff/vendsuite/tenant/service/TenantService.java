package com.packapuff.vendsuite.tenant.service;


import com.packapuff.vendsuite.tenant.service.dto.CreateTenantRequest;
import com.packapuff.vendsuite.tenant.service.dto.GetTenantsRequest;
import com.packapuff.vendsuite.tenant.service.dto.TenantResponse;
import com.packapuff.vendsuite.tenant.service.dto.UpdateTenantRequest;

import java.util.List;

public interface TenantService {

    TenantResponse createTenant(CreateTenantRequest createTenantRequest);

    List<TenantResponse> getTenants(GetTenantsRequest getTenantsRequest);

    List<TenantResponse> updateTenants(List<UpdateTenantRequest> updateTenantRequestList);

    TenantResponse deleteTenant(Integer tenantId);
    
}
