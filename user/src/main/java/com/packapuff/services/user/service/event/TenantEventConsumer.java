package com.packapuff.services.user.service.event;

import com.packapuff.services.user.service.UserService;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.PointOfSaleEvent;
import com.packapuff.vendsuite.common.event_models.model.TenantEvent;
import com.packapuff.vendsuite.tenant.service.dto.TenantResponse;
import com.packapuff.vendsuite.tenant.service.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class TenantEventConsumer implements ApplicationListener<TenantEvent> {

    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(TenantEvent event) {
        System.out.println("Received PointOfSaleEvent from PointOfSaleEventConsumer::onApplicationEvent");
        try {

            // DELETED event type
            if (event.getEventType().equals(CrudEventType.DELETED.getValue())) {

                TenantResponse tenantResponse = (TenantResponse) event.getSource();

                userService.deleteUsersOfTenant(tenantResponse.getTenantId());

            }

        } catch(Exception e) {
            System.out.println("Received bad PointOfSaleEvent");
        }
        System.out.println("Received PointOfSaleEvent - " + event.getSource().toString());
    }

}
