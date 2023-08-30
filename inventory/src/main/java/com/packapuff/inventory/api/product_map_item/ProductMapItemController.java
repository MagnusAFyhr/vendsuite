package com.packapuff.inventory.api.product_map_item;

import com.packapuff.inventory.service.dto.product_map_item.CreateProductMapItemRequest;
import com.packapuff.inventory.service.dto.product_map_item.GetProductMapItemsRequest;
import com.packapuff.inventory.service.dto.product_map_item.ProductMapItemResponse;
import com.packapuff.inventory.service.dto.product_map_item.UpdateProductMapItemRequest;
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
@RequestMapping("/inventory/productMapItem")
public interface ProductMapItemController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductMapItemResponse>> createProductMapItems(@Valid @RequestBody List<CreateProductMapItemRequest> requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductMapItemResponse>> getProductMapItems(@Valid @RequestBody GetProductMapItemsRequest requestBody);

    @PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductMapItemResponse>> updateProductMapItems(@Valid @RequestBody List<UpdateProductMapItemRequest> requestBody);

    @DeleteMapping(value = "/{productMapId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductMapItemResponse>> deleteProductMapItems(@Valid @PathVariable("productMapId") @NotNull Integer productMapId);

}
