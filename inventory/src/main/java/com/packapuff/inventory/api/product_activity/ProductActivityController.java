package com.packapuff.inventory.api.product_activity;

import com.packapuff.inventory.service.dto.product_activity.CreateProductActivityRequest;
import com.packapuff.inventory.service.dto.product_activity.GetProductActivitiesRequest;
import com.packapuff.inventory.service.dto.product_activity.ProductActivityResponse;
import com.packapuff.inventory.service.dto.product_activity.UpdateProductActivityRequest;
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
@RequestMapping("/inventory/productActivity")
public interface ProductActivityController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ProductActivityResponse> createProductActivity(@Valid @RequestBody CreateProductActivityRequest requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<ProductActivityResponse>> getProductActivities(@Valid @RequestBody GetProductActivitiesRequest requestBody);

//    @PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
//    ResponseEntity<List<ProductActivityResponse>> updateProductActivities(@Valid @RequestBody List<UpdateProductActivityRequest> requestBody);

    @DeleteMapping(value = "/{productActivityId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ProductActivityResponse> deleteProductActivity(@Valid @PathVariable("productActivityId") @NotNull Integer productActivityId);

}
