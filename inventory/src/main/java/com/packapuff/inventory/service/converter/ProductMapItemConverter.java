package com.packapuff.inventory.service.converter;


import com.packapuff.inventory.service.dto.product_map_item.CreateProductMapItemRequest;
import com.packapuff.inventory.service.dto.product_map_item.ProductMapItemResponse;
import com.packapuff.inventory.service.entity.ProductMapItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapItemConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMapItemConverter.class);

    public ProductMapItem convertToProductMapItemEntity(CreateProductMapItemRequest request) {
        LOGGER.trace("Entering ProductMapItemConverter::convertToProductMapItemEntity");

        ProductMapItem productMapItem = new ProductMapItem();

        productMapItem.setProductMapId(request.getProductMapId());
        productMapItem.setProductId(request.getProductId());
        productMapItem.setMdbCode(request.getMdbCode());

        productMapItem.setMapX(request.getMapX());
        productMapItem.setMapY(request.getMapY());

        productMapItem.setCreatedTimestamp(OffsetDateTime.now());
        productMapItem.setUpdatedTimestamp(OffsetDateTime.now());

        LOGGER.trace("Exiting ProductMapItemConverter::convertToProductMapItemEntity");
        return productMapItem;
    }

    public List<ProductMapItem> convertToProductMapItemEntities(List<CreateProductMapItemRequest> requests) {
        LOGGER.trace("Entering ProductMapItemConverter::convertToProductMapItemEntities");

        List<ProductMapItem> responseList = new ArrayList<>();

        for (CreateProductMapItemRequest request: requests) {
            responseList.add(convertToProductMapItemEntity(request));
        }

        LOGGER.trace("Exiting ProductMapItemConverter::convertToProductMapItemEntities");
        return responseList;
    }

    public ProductMapItemResponse convertToProductMapItemResponse(ProductMapItem productMapItem) {
        LOGGER.debug("Entering ProductMapItemConverter::convertToProductMapItemResponse");

        ProductMapItemResponse response = new ProductMapItemResponse();

        response.setProductMapItemId(productMapItem.getProductMapItemId());
        response.setProductMapId(productMapItem.getProductMapId());
        response.setProductId(productMapItem.getProductId());
        response.setMdbCode(productMapItem.getMdbCode());

        response.setMapX(productMapItem.getMapX());
        response.setMapY(productMapItem.getMapY());

        response.setCreatedTimestamp(productMapItem.getCreatedTimestamp());
        response.setUpdatedTimestamp(productMapItem.getUpdatedTimestamp());

        LOGGER.debug("Exiting ProductMapItemConverter::convertToProductMapItemResponse");
        return response;
    }

    public List<ProductMapItemResponse> convertToProductMapItemResponseList(List<ProductMapItem> productMapItemList) {
        LOGGER.trace("Entering ProductMapItemConverter::convertToProductMapItemResponseList");

        List<ProductMapItemResponse> responseList = new ArrayList<>();

        for (ProductMapItem productMapItem: productMapItemList) {
            responseList.add(convertToProductMapItemResponse(productMapItem));
        }

        LOGGER.trace("Exiting ProductMapItemConverter::convertToProductMapItemResponseList");
        return responseList;
    }
}
