package com.packapuff.inventory.service.converter;


import com.packapuff.inventory.service.dto.product.ProductResponse;
import com.packapuff.inventory.service.dto.product.CreateProductRequest;
import com.packapuff.inventory.service.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConverter.class);

    public Product convertToProductEntity(CreateProductRequest request) {
        LOGGER.trace("Entering convertToProductEntity");

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());

        product.setCreatedTimestamp(OffsetDateTime.now());
        product.setUpdatedTimestamp(OffsetDateTime.now());

        LOGGER.trace("Exiting convertToProductEntity");
        return product;
    }

    public ProductResponse convertToProductResponse(Product product) {
        LOGGER.debug("Entering convertToProductResponse");

        ProductResponse response = new ProductResponse();

        response.setProductId(product.getProductId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());

        response.setCreatedTimestamp(product.getCreatedTimestamp());
        response.setUpdatedTimestamp(product.getUpdatedTimestamp());

        LOGGER.debug("Exiting convertToProductResponse");
        return response;
    }

    public List<ProductResponse> convertToProductResponseList(List<Product> productList) {
        LOGGER.trace("Entering convertToProductResponseList");

        List<ProductResponse> responseList = new ArrayList<>();

        for (Product product: productList) {
            responseList.add(convertToProductResponse(product));
        }

        LOGGER.trace("Exiting convertToProductResponseList");
        return responseList;
    }
}
