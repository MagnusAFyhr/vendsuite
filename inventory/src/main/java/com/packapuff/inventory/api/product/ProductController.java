package com.packapuff.inventory.api.product;

import com.packapuff.inventory.service.dto.product.ProductResponse;
import com.packapuff.inventory.service.dto.product.CreateProductRequest;
import com.packapuff.inventory.service.dto.product.GetProductsRequest;
import com.packapuff.inventory.service.dto.product.UpdateProductRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * [Insert Comments]
 *
 */

@Validated
@RequestMapping("/inventory/product")
public interface ProductController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductResponse>> getProducts(@Valid @RequestBody GetProductsRequest requestBody);

    @PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductResponse>> updateProducts(@Valid @RequestBody List<UpdateProductRequest> requestBody);

    @DeleteMapping(value = "/{productId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ProductResponse> deleteProduct(@Valid @PathVariable("productId") @NotNull Integer productId);

}
