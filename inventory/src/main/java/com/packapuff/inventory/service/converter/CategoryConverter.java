package com.packapuff.inventory.service.converter;


import com.packapuff.inventory.service.dto.category.CategoryResponse;
import com.packapuff.inventory.service.dto.category.CreateCategoryRequest;
import com.packapuff.inventory.service.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryConverter.class);

    public Category convertToCategoryEntity(CreateCategoryRequest request) {
        LOGGER.trace("Entering convertToCategoryEntity");

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        category.setCreatedTimestamp(OffsetDateTime.now());
        category.setUpdatedTimestamp(OffsetDateTime.now());

        LOGGER.trace("Exiting convertToCategoryEntity");
        return category;
    }

    public CategoryResponse convertToCategoryResponse(Category category) {
        LOGGER.debug("Entering convertToCategoryResponse");

        CategoryResponse response = new CategoryResponse();

        response.setCategoryId(category.getCategoryId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());

        response.setCreatedTimestamp(category.getCreatedTimestamp());
        response.setUpdatedTimestamp(category.getUpdatedTimestamp());

        LOGGER.debug("Exiting convertToCategoryResponse");
        return response;
    }

    public List<CategoryResponse> convertToCategoryResponseList(List<Category> categoryList) {
        LOGGER.trace("Entering convertToCategoryResponseList");

        List<CategoryResponse> responseList = new ArrayList<>();

        for (Category category: categoryList) {
            responseList.add(convertToCategoryResponse(category));
        }

        LOGGER.trace("Exiting convertToCategoryResponseList");
        return responseList;
    }
}
