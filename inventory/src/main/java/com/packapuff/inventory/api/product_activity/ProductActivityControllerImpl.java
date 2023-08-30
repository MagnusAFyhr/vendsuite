package com.packapuff.inventory.api.product_activity;

import com.packapuff.inventory.service.dto.product_activity.CreateProductActivityRequest;
import com.packapuff.inventory.service.dto.product_activity.GetProductActivitiesRequest;
import com.packapuff.inventory.service.dto.product_activity.ProductActivityResponse;
import com.packapuff.inventory.service.dto.product_activity.UpdateProductActivityRequest;
import com.packapuff.inventory.service.product_activity.ProductActivityService;
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
public class ProductActivityControllerImpl implements ProductActivityController {

    private static final Logger log = LoggerFactory.getLogger(ProductActivityControllerImpl.class);

    @Autowired
    private ProductActivityService productActivityService;

    /**
     *
     * Creates a productActivity and stores in DB
     *
     * @parameter RequestBody CreateProductActivityRequest
     *
     */
    public ResponseEntity<ProductActivityResponse> createProductActivity(
            @Valid @RequestBody CreateProductActivityRequest createProductActivityRequest
    ) {
        log.trace("Entering createProductActivity");

        ProductActivityResponse productActivity = productActivityService.createProductActivity(createProductActivityRequest);

        log.trace("Exiting createProductActivity");
        return new ResponseEntity<>(productActivity, HttpStatus.OK);
    }

    /**
     *
     * Gets a productActivity from DB
     *
     * @parameter Integer productActivityId
     *
     */
    public ResponseEntity<List<ProductActivityResponse>> getProductActivities(
            @Valid @RequestBody GetProductActivitiesRequest getProductActivitiesRequest
    ) {
        log.trace("Entering getProductActivity");

        List<ProductActivityResponse> productActivities = productActivityService.getProductActivities(getProductActivitiesRequest);

        log.trace("Exiting getProductActivity");
        return new ResponseEntity<>(productActivities, HttpStatus.OK);
    }

//    /**
//     *
//     * Update productActivitys in DB from list
//     *
//     * @parameter RequestBody List<UpdateProductActivityRequest>
//     *
//     */
//    public ResponseEntity<List<ProductActivityResponse>> updateProductActivities(
//            @Valid @RequestBody List<UpdateProductActivityRequest> requestBody
//    ) {
//        log.trace("Entering updateProductActivities");
//
//        List<ProductActivityResponse> productActivities = productActivityService.updateProductActivities(requestBody);
//
//        log.trace("Exiting updateProductActivities");
//        return new ResponseEntity<>(productActivities, HttpStatus.OK);
//    }

    /**
     *
     * Delete productActivity from DB
     *
     * @parameter RequestBody List<UpdateProductActivityRequest>
     *
     */
    public ResponseEntity<ProductActivityResponse> deleteProductActivity(Integer productActivityId) {
        log.trace("Entering deleteProductActivity");

        ProductActivityResponse productActivity = productActivityService.deleteProductActivity(productActivityId);

        log.trace("Exiting deleteProductActivity");
        return new ResponseEntity<>(productActivity, HttpStatus.OK);
    }

}
