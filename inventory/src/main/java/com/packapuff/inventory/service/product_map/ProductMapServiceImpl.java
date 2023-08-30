package com.packapuff.inventory.service.product_map;

import com.packapuff.inventory.service.event.ProductMapEventProducer;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.inventory.service.converter.ProductMapConverter;
import com.packapuff.inventory.service.dto.product_map.CreateProductMapRequest;
import com.packapuff.inventory.service.dto.product_map.GetProductMapsRequest;
import com.packapuff.inventory.service.dto.product_map.ProductMapResponse;
import com.packapuff.inventory.service.dto.product_map.UpdateProductMapRequest;
import com.packapuff.inventory.service.entity.ProductMap;
import com.packapuff.inventory.service.repository.ProductMapRepository;
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
public class ProductMapServiceImpl implements ProductMapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMapServiceImpl.class);

    @Autowired
    private ProductMapRepository productMapRepository;

    @Autowired
    private ProductMapConverter productMapConverter;

    @Autowired
    private InventoryUtilities utils;
    
    @Autowired
    private ProductMapEventProducer productMapEventProducer;

    public ProductMapResponse createProductMap(CreateProductMapRequest createProductMapRequest) {
        LOGGER.trace("Creating productMap");

        ProductMapResponse productMapResponse;
        try {
            // convert user request to user object
            ProductMap productMap = productMapConverter.convertToProductMapEntity(createProductMapRequest);

            // push to db and return db object
            ProductMap productMapEntity = productMapRepository.save(productMap); // this will throw an exception

            // convert entity to response
            productMapResponse = productMapConverter.convertToProductMapResponse(productMapEntity);
                    
            // emit event
            productMapEventProducer.publishProductMapCreatedEvent(productMapResponse);

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

        LOGGER.trace("Created productMap");
        return productMapResponse;
    }

    public List<ProductMapResponse> getProductMaps(GetProductMapsRequest getProductMapsRequest) {
        LOGGER.trace("Retrieving productMaps (get)");

        List<ProductMapResponse> productMapResponses;
        try {
            // get object from db
            List<ProductMap> productMapEntities = productMapRepository.findByProductMapIdIn(getProductMapsRequest.getProductMapIds()).orElseThrow(EntityNotFoundException::new);

            // convert entity to response
            productMapResponses = productMapConverter.convertToProductMapResponseList(productMapEntities);

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
        LOGGER.trace("Retrieved productMaps (get)");
        return productMapResponses;
    }

    public List<ProductMapResponse> updateProductMaps(List<UpdateProductMapRequest> updateProductMapRequestList) {
        LOGGER.trace("Updating productMaps");

        List<ProductMapResponse> productMapResponses;
        try {
            // collect user ids
            List<Integer> productMapIds = new ArrayList<>();
            for (UpdateProductMapRequest updateProductMapRequest : updateProductMapRequestList) {
                productMapIds.add(updateProductMapRequest.getProductMapId());
            }

            // get user entities
            List<ProductMap> productMaps = productMapRepository.findByProductMapIdIn(productMapIds).orElseThrow(RuntimeException::new);
            // update user entities
            for (int i = 0; i < productMaps.size() - 1; i++) {
                // product map name
                if (!utils.isBlank(updateProductMapRequestList.get(i).getProductMapName())) {
                    productMaps.get(i).setProductMapName(updateProductMapRequestList.get(i).getProductMapName());
                }
                // width
                if (null != updateProductMapRequestList.get(i).getWidth()) {
                    productMaps.get(i).setWidth(updateProductMapRequestList.get(i).getWidth());
                }
                // height
                if (null != updateProductMapRequestList.get(i).getHeight()) {
                    productMaps.get(i).setHeight(updateProductMapRequestList.get(i).getHeight());
                }

                productMaps.get(i).setUpdatedTimestamp(OffsetDateTime.now());
            }

            // push to db and return db object
            List<ProductMap> productMapEntities = productMapRepository.saveAll(productMaps); // this will throw an exception

            // convert entity to response
            productMapResponses = productMapConverter.convertToProductMapResponseList(productMapEntities);

            // emit event(s)
            for (ProductMapResponse productMapResponseForEvent: productMapResponses) {
                productMapEventProducer.publishProductMapUpdatedEvent(productMapResponseForEvent);
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
        LOGGER.trace("Updated productMaps");
        return productMapResponses;
    }

    public ProductMapResponse deleteProductMap(Integer productMapId) {
        LOGGER.trace("Deleting productMap: {}", productMapId);

        ProductMapResponse productMapResponse;
        try {
            // delete productMap
            ProductMap productMap = productMapRepository.findById(productMapId)
                    .orElseThrow(EntityNotFoundException::new);
            productMapRepository.delete(productMap);

            // convert entity to response
            productMapResponse = productMapConverter.convertToProductMapResponse(productMap);

            // emit event
            productMapEventProducer.publishProductMapDeletedEvent(productMapResponse);

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
        LOGGER.trace("Deleted productMap: {}", productMapId);
        return productMapResponse;
    }

}
