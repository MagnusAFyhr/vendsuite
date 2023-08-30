package com.packapuff.vendsuite.tenant.service.event;

import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.TenantEvent;
import com.packapuff.vendsuite.tenant.service.dto.TenantResponse;
import com.packapuff.vendsuite.tenant.service.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class TenantEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishTenantCreatedEvent(final TenantResponse tenantResponse) {
        System.out.println("publishTenantCreatedEvent");
        TenantEvent tenantCreatedEvent = new TenantEvent(
                tenantResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(tenantCreatedEvent);
    }

    public void publishTenantDeletedEvent(final TenantResponse tenantResponse) {
        System.out.println("publishTenantCreatedEvent");
        TenantEvent tenantDeletedEvent = new TenantEvent(
                tenantResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(tenantDeletedEvent);
    }

    public void publishTenantUpdatedEvent(final TenantResponse tenantResponse) {
        System.out.println("publishTenantCreatedEvent");
        TenantEvent tenantUpdatedEvent = new TenantEvent(
                tenantResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(tenantUpdatedEvent);
    }
}
