package com.packapuff.inventory.api.product_map;

import com.packapuff.inventory.api.product_map.ProductMapController;
import com.packapuff.inventory.service.dto.product_map.CreateProductMapRequest;
import com.packapuff.inventory.service.dto.product_map.GetProductMapsRequest;
import com.packapuff.inventory.service.dto.product_map.ProductMapResponse;
import com.packapuff.inventory.service.dto.product_map.UpdateProductMapRequest;
import com.packapuff.inventory.service.product_map.ProductMapService;
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
public class ProductMapControllerImpl implements ProductMapController {

    private static final Logger log = LoggerFactory.getLogger(ProductMapControllerImpl.class);

    @Autowired
    private ProductMapService productMapService;

    /**
     *
     * Creates a productMap and stores in DB
     *
     * @param createProductMapRequest CreateProductMapRequest object
     *
     */
    public ResponseEntity<ProductMapResponse> createProductMap(
            @Valid @RequestBody CreateProductMapRequest createProductMapRequest
    ) {
        log.trace("Entering createProductMap");

        ProductMapResponse productMap = productMapService.createProductMap(createProductMapRequest);

        log.trace("Exiting createProductMap");
        return new ResponseEntity<>(productMap, HttpStatus.OK);
    }

    /**
     *
     * Gets a productMap from DB
     *
     * @param getProductMapsRequest GetProductMapsRequest object
     *
     */
    public ResponseEntity<List<ProductMapResponse>> getProductMaps(
            @Valid @RequestBody GetProductMapsRequest getProductMapsRequest
    ) {
        log.trace("Entering getProductMap");

        List<ProductMapResponse> productMaps = productMapService.getProductMaps(getProductMapsRequest);

        log.trace("Exiting getProductMap");
        return new ResponseEntity<>(productMaps, HttpStatus.OK);
    }

    /**
     *
     * Update productMaps in DB from list
     *
     * @param requestBody List<UpdateProductMapRequest> object
     *
     */
    public ResponseEntity<List<ProductMapResponse>> updateProductMaps(
            @Valid @RequestBody List<UpdateProductMapRequest> requestBody
    ) {
        log.trace("Entering updateProductMaps");

        List<ProductMapResponse> productMaps = productMapService.updateProductMaps(requestBody);

        log.trace("Exiting updateProductMaps");
        return new ResponseEntity<>(productMaps, HttpStatus.OK);
    }

    /**
     *
     * Delete productMap from DB
     *
     * @param productMapId Identifier of productMap
     *
     */
    public ResponseEntity<ProductMapResponse> deleteProductMap(Integer productMapId) {
        log.trace("Entering deleteProductMap");

        ProductMapResponse productMap = productMapService.deleteProductMap(productMapId);

        log.trace("Exiting deleteProductMap");
        return new ResponseEntity<>(productMap, HttpStatus.OK);
    }

}
