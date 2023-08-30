package com.packapuff.inventory.service.converter;


import com.packapuff.inventory.service.dto.product_activity.CreateProductActivityRequest;
import com.packapuff.inventory.service.dto.product_activity.ProductActivityResponse;
import com.packapuff.inventory.service.entity.ProductActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductActivityConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductActivityConverter.class);

    public ProductActivity convertToProductActivityEntity(CreateProductActivityRequest request) {
        LOGGER.trace("Entering convertToProductActivityEntity");

        ProductActivity productActivity = new ProductActivity();

        productActivity.setProductActivityType(request.getType());
        productActivity.setProductId(request.getProductId());
        productActivity.setProductAmount(request.getProductAmount());
        productActivity.setParentProductActivityId(request.getParentProductActivityId());
        productActivity.setSourceEntityType(request.getSourceEntityType());
        productActivity.setSourceEntityId(request.getSourceEntityId());

        productActivity.setCreatedTimestamp(OffsetDateTime.now());
        productActivity.setUpdatedTimestamp(OffsetDateTime.now());

        LOGGER.trace("Exiting convertToProductActivityEntity");
        return productActivity;
    }

    public ProductActivityResponse convertToProductActivityResponse(ProductActivity productActivity) {
        LOGGER.debug("Entering convertToProductActivityResponse");

        ProductActivityResponse response = new ProductActivityResponse();

        response.setProductActivityId(productActivity.getProductActivityId());
        response.setProductActivityType(productActivity.getProductActivityType());
        response.setProductId(productActivity.getProductId());
        response.setProductAmount(productActivity.getProductAmount());
        response.setParentProductActivityId(productActivity.getParentProductActivityId());
        response.setSourceEntityType(productActivity.getSourceEntityType());
        response.setSourceEntityId(productActivity.getSourceEntityId());

        response.setCreatedTimestamp(productActivity.getCreatedTimestamp());
        response.setUpdatedTimestamp(productActivity.getUpdatedTimestamp());

        LOGGER.debug("Exiting convertToProductActivityResponse");
        return response;
    }

    public List<ProductActivityResponse> convertToProductActivityResponseList(List<ProductActivity> productActivityList) {
        LOGGER.trace("Entering convertToProductActivityResponseList");

        List<ProductActivityResponse> responseList = new ArrayList<>();

        for (ProductActivity productActivity: productActivityList) {
            responseList.add(convertToProductActivityResponse(productActivity));
        }

        LOGGER.trace("Exiting convertToProductActivityResponseList");
        return responseList;
    }
}
