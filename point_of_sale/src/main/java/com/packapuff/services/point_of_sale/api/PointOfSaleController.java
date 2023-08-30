package com.packapuff.services.point_of_sale.api;


import com.packapuff.services.point_of_sale.service.dto.CreatePointOfSaleRequest;
import com.packapuff.services.point_of_sale.service.dto.ListPointOfSalesRequest;
import com.packapuff.services.point_of_sale.service.dto.PointOfSaleResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 *
 * [Insert Comments]
 *
 */

@Validated
@RequestMapping("/pointOfSales")
public interface PointOfSaleController {


    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<PointOfSaleResponse> createPointOfSale(@Valid @RequestBody CreatePointOfSaleRequest requestBody);

    @GetMapping(value = "/{pointOfSaleId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<PointOfSaleResponse>> getPointOfSales(@Valid @PathVariable("pointOfSaleIds") @NotNull List<Integer> pointOfSaleId);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<PointOfSaleResponse>> listPointOfSales(@Valid @RequestBody ListPointOfSalesRequest requestBody);
}
