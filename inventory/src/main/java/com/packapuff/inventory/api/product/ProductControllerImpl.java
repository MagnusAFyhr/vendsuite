package com.packapuff.inventory.api.product;

import com.packapuff.inventory.service.dto.product.ProductResponse;
import com.packapuff.inventory.service.dto.product.CreateProductRequest;
import com.packapuff.inventory.service.dto.product.GetProductsRequest;
import com.packapuff.inventory.service.dto.product.UpdateProductRequest;
import com.packapuff.inventory.service.product.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * [Insert Comments]
 *
 */
@RestController
public class ProductControllerImpl implements ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductControllerImpl.class);

    @Autowired
    private ProductService productService;

    /**
     *
     * Creates a product and stores in DB
     *
     * @param createProductRequest CreateProductRequest object
     *
     */
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        log.trace("Entering createProduct");

        ProductResponse product = productService.createProduct(createProductRequest);

        log.trace("Exiting createProduct");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     *
     * Gets a product from DB
     *
     * @param getProductsRequest GetProductsRequest object
     *
     */
    public ResponseEntity<List<ProductResponse>> getProducts(
            @Valid @RequestBody GetProductsRequest getProductsRequest
    ) {
        log.trace("Entering getProduct");

        List<ProductResponse> products = productService.getProducts(getProductsRequest);

        log.trace("Exiting getProduct");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     *
     * Update products in DB from list
     *
     * @param requestBody List<UpdateProductRequest> object
     *
     */
    public ResponseEntity<List<ProductResponse>> updateProducts(
            @Valid @RequestBody List<UpdateProductRequest> requestBody
    ) {
        log.trace("Entering updateProducts");

        List<ProductResponse> products = productService.updateProducts(requestBody);

        log.trace("Exiting updateProducts");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     *
     * Delete product from DB
     *
     * @param productId Identifier of product
     *
     */
    public ResponseEntity<ProductResponse> deleteProduct(Integer productId) {
        log.trace("Entering deleteProduct");

        ProductResponse product = productService.deleteProduct(productId);

        log.trace("Exiting deleteProduct");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
