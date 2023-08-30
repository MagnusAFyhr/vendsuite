package com.packapuff.inventory.api.product_map;

import com.packapuff.inventory.service.dto.product_map.CreateProductMapRequest;
import com.packapuff.inventory.service.dto.product_map.GetProductMapsRequest;
import com.packapuff.inventory.service.dto.product_map.ProductMapResponse;
import com.packapuff.inventory.service.dto.product_map.UpdateProductMapRequest;
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
@RequestMapping("/inventory/productMap")
public interface ProductMapController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ProductMapResponse> createProductMap(@Valid @RequestBody CreateProductMapRequest requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductMapResponse>> getProductMaps(@Valid @RequestBody GetProductMapsRequest requestBody);

    @PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductMapResponse>> updateProductMaps(@Valid @RequestBody List<UpdateProductMapRequest> requestBody);

    @DeleteMapping(value = "/{productMapId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ProductMapResponse> deleteProductMap(@Valid @PathVariable("productMapId") @NotNull Integer productMapId);

}
