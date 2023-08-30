package com.packapuff.inventory.service.converter;


import com.packapuff.inventory.service.dto.product_map.CreateProductMapRequest;
import com.packapuff.inventory.service.dto.product_map.ProductMapResponse;
import com.packapuff.inventory.service.entity.ProductMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMapConverter.class);

    public ProductMap convertToProductMapEntity(CreateProductMapRequest request) {
        LOGGER.trace("Entering ProductMapConverter::convertToProductMapEntity");

        ProductMap productMap = new ProductMap();

        productMap.setProductMapName(request.getProductMapName());
        productMap.setWidth(request.getWidth());
        productMap.setHeight(request.getHeight());

        productMap.setCreatedTimestamp(OffsetDateTime.now());
        productMap.setUpdatedTimestamp(OffsetDateTime.now());

        LOGGER.trace("Exiting ProductMapConverter::convertToProductMapEntity");
        return productMap;
    }

    public ProductMapResponse convertToProductMapResponse(ProductMap productMap) {
        LOGGER.debug("Entering ProductMapConverter::convertToProductMapResponse");

        ProductMapResponse response = new ProductMapResponse();

        response.setProductMapId(productMap.getProductMapId());
        response.setProductMapName(productMap.getProductMapName());
        response.setWidth(productMap.getWidth());
        response.setHeight(productMap.getHeight());

        response.setCreatedTimestamp(productMap.getCreatedTimestamp());
        response.setUpdatedTimestamp(productMap.getUpdatedTimestamp());

        LOGGER.debug("Exiting ProductMapConverter::convertToProductMapResponse");
        return response;
    }

    public List<ProductMapResponse> convertToProductMapResponseList(List<ProductMap> productMapList) {
        LOGGER.trace("Entering ProductMapConverter::convertToProductMapResponseList");

        List<ProductMapResponse> responseList = new ArrayList<>();

        for (ProductMap productMap: productMapList) {
            responseList.add(convertToProductMapResponse(productMap));
        }

        LOGGER.trace("Exiting ProductMapConverter::convertToProductMapResponseList");
        return responseList;
    }
}
