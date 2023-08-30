package com.packapuff.inventory.service.product_map_item;

import com.packapuff.inventory.service.event.ProductMapItemEventProducer;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.inventory.service.converter.ProductMapItemConverter;
import com.packapuff.inventory.service.dto.product_map_item.CreateProductMapItemRequest;
import com.packapuff.inventory.service.dto.product_map_item.GetProductMapItemsRequest;
import com.packapuff.inventory.service.dto.product_map_item.ProductMapItemResponse;
import com.packapuff.inventory.service.dto.product_map_item.UpdateProductMapItemRequest;
import com.packapuff.inventory.service.entity.ProductMapItem;
import com.packapuff.inventory.service.repository.ProductMapItemRepository;
import com.packapuff.inventory.service.utilities.InventoryUtilities;
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
public class ProductMapItemServiceImpl implements ProductMapItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMapItemServiceImpl.class);

    @Autowired
    private ProductMapItemRepository productMapItemRepository;

    @Autowired
    private ProductMapItemConverter productMapItemConverter;

    @Autowired
    private InventoryUtilities utils;
    
    @Autowired
    private ProductMapItemEventProducer productMapItemEventProducer;

    public List<ProductMapItemResponse> createProductMapItems(List<CreateProductMapItemRequest> createProductMapItemRequestList) {
        LOGGER.trace("Creating productMapItem");

        List<ProductMapItemResponse> productMapItemResponses;
        try {
            // convert product map item request to user object
            List<ProductMapItem> productMapItems = productMapItemConverter.convertToProductMapItemEntities(createProductMapItemRequestList);

            // push to db and return db object
            List<ProductMapItem> productMapItemEntities = productMapItemRepository.saveAll(productMapItems); // this will throw an exception

            // convert entity to response
            productMapItemResponses = productMapItemConverter.convertToProductMapItemResponseList(productMapItemEntities);
            
            // emit event(s)
            for (ProductMapItemResponse productMapItemResponseForEvent: productMapItemResponses) {
                productMapItemEventProducer.publishProductMapItemUpdatedEvent(productMapItemResponseForEvent);
            }
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

        LOGGER.trace("Created productMapItem");
        return productMapItemResponses;
    }

    public List<ProductMapItemResponse> getProductMapItems(GetProductMapItemsRequest getProductMapItemsRequest) {
        LOGGER.trace("Retrieving productMapItems (get)");

        List<ProductMapItemResponse> productMapItemResponses;
        try {
            // get object from db
            List<ProductMapItem> productMapItemEntities = productMapItemRepository.findByProductMapIdIn(getProductMapItemsRequest.getProductMapIds()).orElseThrow(NoSuchElementException::new);

            // convert entity to response
            productMapItemResponses = productMapItemConverter.convertToProductMapItemResponseList(productMapItemEntities);

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
        LOGGER.trace("Retrieved productMapItems (get)");
        return productMapItemResponses;
    }

    public List<ProductMapItemResponse> updateProductMapItems(List<UpdateProductMapItemRequest> updateProductMapItemRequestList) {
        LOGGER.trace("Updating productMapItems");

        List<ProductMapItemResponse> productMapItemResponses;
        try {
            // collect user ids
            List<Integer> productMapItemIds = new ArrayList<>();
            for (UpdateProductMapItemRequest updateProductMapItemRequest : updateProductMapItemRequestList) {
                productMapItemIds.add(updateProductMapItemRequest.getProductMapItemId());
            }

            // get product map item entities
            List<ProductMapItem> productMapItems = productMapItemRepository.findByProductMapIdIn(productMapItemIds).orElseThrow(RuntimeException::new);
            // update user entities
            for (int i = 0; i < productMapItems.size() - 1; i++) {
                // product id
                if (null != updateProductMapItemRequestList.get(i).getProductId()) {
                    productMapItems.get(i).setProductId(updateProductMapItemRequestList.get(i).getProductId());
                }

                productMapItems.get(i).setUpdatedTimestamp(OffsetDateTime.now());
            }

            // push to db and return db object
            List<ProductMapItem> productMapItemEntities = productMapItemRepository.saveAll(productMapItems); // this will throw an exception

            // convert entity to response
            productMapItemResponses = productMapItemConverter.convertToProductMapItemResponseList(productMapItemEntities);

            // emit event(s)
            for (ProductMapItemResponse productMapItemResponseForEvent: productMapItemResponses) {
                productMapItemEventProducer.publishProductMapItemUpdatedEvent(productMapItemResponseForEvent);
            }
            
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

        // convert db objects into user response objects; and return
        LOGGER.trace("Updated productMapItems");
        return productMapItemResponses;
    }

    public List<ProductMapItemResponse> deleteProductMapItems(Integer productMapId) {
        LOGGER.trace("Deleting productMapItems for map : {}", productMapId);

        List<ProductMapItemResponse> productMapItemResponses;
        try {
            // delete productMapItem
            List<ProductMapItem> productMapItems = productMapItemRepository.findByProductMapId(productMapId).orElseThrow(NoSuchElementException::new);
            productMapItemRepository.deleteAllInBatch(productMapItems);

            // convert entity to response
            productMapItemResponses = productMapItemConverter.convertToProductMapItemResponseList(productMapItems);

            // emit event(s)
            for (ProductMapItemResponse productMapItemResponseForEvent: productMapItemResponses) {
                productMapItemEventProducer.publishProductMapItemUpdatedEvent(productMapItemResponseForEvent);
            }
            
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
        LOGGER.trace("Deleting productMapItems for map : {}", productMapId);
        return productMapItemResponses;
    }

}
