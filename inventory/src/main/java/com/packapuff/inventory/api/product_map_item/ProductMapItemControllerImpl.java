package com.packapuff.inventory.api.product_map_item;

import com.packapuff.inventory.service.dto.product_map_item.CreateProductMapItemRequest;
import com.packapuff.inventory.service.dto.product_map_item.GetProductMapItemsRequest;
import com.packapuff.inventory.service.dto.product_map_item.ProductMapItemResponse;
import com.packapuff.inventory.service.dto.product_map_item.UpdateProductMapItemRequest;
import com.packapuff.inventory.service.product_map_item.ProductMapItemService;
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
public class ProductMapItemControllerImpl implements ProductMapItemController {

    private static final Logger log = LoggerFactory.getLogger(ProductMapItemControllerImpl.class);

    @Autowired
    private ProductMapItemService productMapItemService;

    /**
     *
     * Creates productMapItems and stores in DB
     *
     * @param createProductMapItemRequestList list of CreateProductMapItemRequest objects
     *
     */
    public ResponseEntity<List<ProductMapItemResponse>> createProductMapItems(
            @Valid @RequestBody List<CreateProductMapItemRequest> createProductMapItemRequestList
    ) {
        log.trace("Entering createProductMapItem");

        List<ProductMapItemResponse> productMapItems = productMapItemService.createProductMapItems(createProductMapItemRequestList);

        log.trace("Exiting createProductMapItem");
        return new ResponseEntity<>(productMapItems, HttpStatus.OK);
    }

    /**
     *
     * Gets a productMapItem from DB
     *
     * @param getProductMapItemsRequest GetProductMapItemsRequest object
     *
     */
    public ResponseEntity<List<ProductMapItemResponse>> getProductMapItems(
            @Valid @RequestBody GetProductMapItemsRequest getProductMapItemsRequest
    ) {
        log.trace("Entering getProductMapItem");

        List<ProductMapItemResponse> productMapItems = productMapItemService.getProductMapItems(getProductMapItemsRequest);

        log.trace("Exiting getProductMapItem");
        return new ResponseEntity<>(productMapItems, HttpStatus.OK);
    }

    /**
     *
     * Update productMapItems in DB from list
     *
     * @param requestBody List<UpdateProductMapItemRequest> object
     *
     */
    public ResponseEntity<List<ProductMapItemResponse>> updateProductMapItems(
            @Valid @RequestBody List<UpdateProductMapItemRequest> requestBody
    ) {
        log.trace("Entering updateProductMapItems");

        List<ProductMapItemResponse> productMapItems = productMapItemService.updateProductMapItems(requestBody);

        log.trace("Exiting updateProductMapItems");
        return new ResponseEntity<>(productMapItems, HttpStatus.OK);
    }

    /**
     *
     * Delete productMapItem from DB
     *
     * @param productMapItemId Identifier of productMapItem
     *
     */
    public ResponseEntity<List<ProductMapItemResponse>> deleteProductMapItems(Integer productMapItemId) {
        log.trace("Entering deleteProductMapItem");

        List<ProductMapItemResponse> productMapItemResponseList = productMapItemService.deleteProductMapItems(productMapItemId);

        log.trace("Exiting deleteProductMapItem");
        return new ResponseEntity<>(productMapItemResponseList, HttpStatus.OK);
    }

}
