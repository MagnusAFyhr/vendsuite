package com.packapuff.services.point_of_sale.api;


import com.packapuff.services.point_of_sale.service.PointOfSaleService;
import com.packapuff.services.point_of_sale.service.dto.CreatePointOfSaleRequest;
import com.packapuff.services.point_of_sale.service.dto.ListPointOfSalesRequest;
import com.packapuff.services.point_of_sale.service.dto.PointOfSaleResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 *
 * [Insert Comments]
 *
 */
@RestController
public class PointOfSaleControllerImpl implements PointOfSaleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointOfSaleControllerImpl.class);

    @Autowired
    private PointOfSaleService pointOfSaleService;

    /**
     *
     * Creates a pointOfSale and stores in DB
     *
     * @param createPointOfSaleRequest request pojo
     *
     */
    public ResponseEntity<PointOfSaleResponse> createPointOfSale(
            @Valid @RequestBody CreatePointOfSaleRequest createPointOfSaleRequest
    ) {
        LOGGER.trace("Entering createPointOfSale");

        PointOfSaleResponse pointOfSale = pointOfSaleService.createPointOfSale(createPointOfSaleRequest);

        LOGGER.trace("Exiting createPointOfSale");
        return new ResponseEntity<>(pointOfSale, HttpStatus.OK);
    }

    /**
     *
     * Gets a pointOfSale from DB
     *
     * @param pointOfSaleIds list of identifiers for point of sales
     *
     */
    public ResponseEntity<List<PointOfSaleResponse>> getPointOfSales(List<Integer> pointOfSaleIds) {
        LOGGER.trace("Entering getPointOfSale");

        List<PointOfSaleResponse> pointOfSaleResponseList = pointOfSaleService.getPointOfSales(pointOfSaleIds);

        LOGGER.trace("Exiting createPointOfSale");
        return new ResponseEntity<>(pointOfSaleResponseList, HttpStatus.OK);
    }

    /**
     *
     * Gets pointOfSales from DB as list
     *
     * @param listPointOfSalesRequest request pojo
     *
     */
    public ResponseEntity<List<PointOfSaleResponse>> listPointOfSales(
            @Valid @RequestBody ListPointOfSalesRequest listPointOfSalesRequest
    ) {
        LOGGER.trace("Entering listPointOfSales");

        List<PointOfSaleResponse> pointOfSales = pointOfSaleService.listPointOfSales(listPointOfSalesRequest);

        LOGGER.trace("Exiting listPointOfSales");
        return new ResponseEntity<>(pointOfSales, HttpStatus.OK);
    }
}
