package com.packapuff.services.site.service.event;

import com.packapuff.services.site.service.dto.SiteResponse;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.SiteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class SiteEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishSiteCreatedEvent(final SiteResponse siteResponse) {
        System.out.println("publishSiteCreatedEvent");
        SiteEvent siteCreatedEvent = new SiteEvent(
                siteResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(siteCreatedEvent);
    }

    public void publishSiteDeletedEvent(final SiteResponse siteResponse) {
        System.out.println("publishSiteCreatedEvent");
        SiteEvent siteDeletedEvent = new SiteEvent(
                siteResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(siteDeletedEvent);
    }

    public void publishSiteUpdatedEvent(final SiteResponse siteResponse) {
        System.out.println("publishSiteCreatedEvent");
        SiteEvent siteUpdatedEvent = new SiteEvent(
                siteResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(siteUpdatedEvent);
    }
}
