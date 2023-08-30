package com.packapuff.inventory.service.event;

import com.packapuff.inventory.service.dto.category.CategoryResponse;
import com.packapuff.inventory.service.entity.Category;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.CategoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class CategoryEventProducer {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCategoryCreatedEvent(final CategoryResponse categoryResponse) {
        System.out.println("publishCategoryCreatedEvent");
        CategoryEvent categoryCreatedEvent = new CategoryEvent(
                categoryResponse, CrudEventType.CREATED);
        applicationEventPublisher.publishEvent(categoryCreatedEvent);
    }

    public void publishCategoryDeletedEvent(final CategoryResponse categoryResponse) {
        System.out.println("publishCategoryCreatedEvent");
        CategoryEvent categoryDeletedEvent = new CategoryEvent(
                categoryResponse, CrudEventType.DELETED);
        applicationEventPublisher.publishEvent(categoryDeletedEvent);
    }

    public void publishCategoryUpdatedEvent(final CategoryResponse categoryResponse) {
        System.out.println("publishCategoryCreatedEvent");
        CategoryEvent categoryUpdatedEvent = new CategoryEvent(
                categoryResponse, CrudEventType.UPDATED);
        applicationEventPublisher.publishEvent(categoryUpdatedEvent);
    }
}
