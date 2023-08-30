package com.packapuff.inventory.service.product_activity;

import com.packapuff.inventory.service.event.ProductActivityEventProducer;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.inventory.service.converter.ProductActivityConverter;
import com.packapuff.inventory.service.dto.product_activity.CreateProductActivityRequest;
import com.packapuff.inventory.service.dto.product_activity.GetProductActivitiesRequest;
import com.packapuff.inventory.service.dto.product_activity.ProductActivityResponse;
import com.packapuff.inventory.service.dto.product_activity.UpdateProductActivityRequest;
import com.packapuff.inventory.service.entity.ProductActivity;
import com.packapuff.inventory.service.repository.ProductActivityRepository;
import com.packapuff.inventory.service.utilities.InventoryUtilities;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary.*;

@Service
public class ProductActivityServiceImpl implements ProductActivityService {

    private static final Logger log = LoggerFactory.getLogger(ProductActivityServiceImpl.class);

    @Autowired
    private ProductActivityRepository productActivityRepository;

    @Autowired
    private ProductActivityConverter productActivityConverter;

    @Autowired
    private InventoryUtilities utils;
    
    @Autowired
    private ProductActivityEventProducer productActivityEventProducer;

    public ProductActivityResponse createProductActivity(CreateProductActivityRequest createProductActivityRequest) {
        log.trace("Creating productActivity");

        ProductActivityResponse productActivityResponse;
        try {
            // convert user request to user object
            ProductActivity productActivity = productActivityConverter.convertToProductActivityEntity(createProductActivityRequest);

            // push to db and return db object
            ProductActivity productActivityEntity = productActivityRepository.save(productActivity); // this will throw an exception

            // convert entity to response
            productActivityResponse = productActivityConverter.convertToProductActivityResponse(productActivityEntity);
            
            // emit event
            productActivityEventProducer.publishProductActivityDeletedEvent(productActivityResponse);
            
        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        log.trace("Created productActivity");
        return productActivityResponse;
    }

    public List<ProductActivityResponse> getProductActivities(GetProductActivitiesRequest getProductActivitiesRequest) {
        log.trace("Retrieving productActivities (get)");

        List<ProductActivityResponse> productActivityResponses;
        try {
            // get object from db
            List<ProductActivity> productActivityEntities = productActivityRepository.findByProductActivityIdIn(getProductActivitiesRequest.getProductActivityIds()).orElseThrow(RuntimeException::new);

            // convert entity to response
            productActivityResponses = productActivityConverter.convertToProductActivityResponseList(productActivityEntities);
            
        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }
        // convert db object to user response object; then return
        log.trace("Retrieved productActivities (get)");
        return productActivityResponses;
    }

//    public List<ProductActivityResponse> updateProductActivities(List<UpdateProductActivityRequest> updateProductActivityRequestList) {
//        log.trace("Updating productActivities");
//
//        // save productActivity entities
//        List<ProductActivity> productActivityEntities;
//        try {
//
//            // collect user ids
//            List<Integer> productActivityIds = new ArrayList<>();
//            for (UpdateProductActivityRequest updateProductActivityRequest : updateProductActivityRequestList) {
//                productActivityIds.add(updateProductActivityRequest.getProductActivityId());
//            }
//
//            // get user entities
//            List<ProductActivity> productActivities = productActivityRepository.findByProductActivityIdIn(productActivityIds).orElseThrow(RuntimeException::new);
//            // update user entities
//            for (int i = 0; i < productActivities.size() - 1; i++) {
//                // productActivityType
//                if (!utils.isBlank(updateProductActivityRequestList.get(i).getProductActivityType())) {
//                    productActivities.get(i).setProductActivityType(updateProductActivityRequestList.get(i).getProductActivityType());
//                }
//                // productId
//                if (null != updateProductActivityRequestList.get(i).getProductId()) {
//                    productActivities.get(i).setProductId(updateProductActivityRequestList.get(i).getProductId());
//                }
//                // productAmount
//                if (null != updateProductActivityRequestList.get(i).getProductId()) {
//                    productActivities.get(i).setProductId(updateProductActivityRequestList.get(i).getProductId());
//                }
//                // parentProductActivityId
//                if (null != updateProductActivityRequestList.get(i).getParentProductActivityId()) {
//                    productActivities.get(i).setParentProductActivityId(updateProductActivityRequestList.get(i).getParentProductActivityId());
//                }
//                // sourceEntityType
//                if (!utils.isBlank(updateProductActivityRequestList.get(i).getSourceEntityType())) {
//                    productActivities.get(i).setSourceEntityType(updateProductActivityRequestList.get(i).getSourceEntityType());
//                }
//                // sourceEntityId
//                if (null != updateProductActivityRequestList.get(i).getSourceEntityId()) {
//                    productActivities.get(i).setSourceEntityId(updateProductActivityRequestList.get(i).getSourceEntityId());
//                }
//
//                productActivities.get(i).setUpdatedTimestamp(OffsetDateTime.now());
//            }
//
//            // push to db and return db object
//            productActivityEntities = productActivityRepository.saveAll(productActivities); // this will throw an exception
//
//        } catch (NoSuchElementException e) {
//            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
//        } catch (DataIntegrityViolationException e) {
//            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
//        } catch (DataAccessResourceFailureException e) {
//            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
//        } catch (DataAccessException e) {
//            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
//        } catch (Exception e) {
//            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
//        }
//
//        // convert db objects into user response objects; and return
//        log.trace("Updated productActivities");
//        return productActivityConverter.convertToProductActivityResponseList(productActivityEntities);
//    }

    public ProductActivityResponse deleteProductActivity(Integer productActivityId) {
        log.trace("Deleting productActivity: {}", productActivityId);

        ProductActivityResponse productActivityResponse;
        try {
            // delete productActivity
            ProductActivity productActivity = productActivityRepository.findById(productActivityId)
                    .orElseThrow(EntityNotFoundException::new);
            productActivityRepository.delete(productActivity);

            // convert entity to response
            productActivityResponse = productActivityConverter.convertToProductActivityResponse(productActivity);
            
            // emit event
            productActivityEventProducer.publishProductActivityDeletedEvent(productActivityResponse);
            
        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        // convert db object into user response object; and return
        log.trace("Deleted productActivity: {}", productActivityId);
        return productActivityResponse;
    }

}
