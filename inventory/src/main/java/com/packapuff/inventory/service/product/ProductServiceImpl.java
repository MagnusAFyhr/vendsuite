package com.packapuff.inventory.service.product;

import com.packapuff.inventory.service.event.ProductEventProducer;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.inventory.service.converter.ProductConverter;
import com.packapuff.inventory.service.dto.product.ProductResponse;
import com.packapuff.inventory.service.dto.product.CreateProductRequest;
import com.packapuff.inventory.service.dto.product.GetProductsRequest;
import com.packapuff.inventory.service.dto.product.UpdateProductRequest;
import com.packapuff.inventory.service.entity.Product;
import com.packapuff.inventory.service.repository.ProductRepository;
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
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private InventoryUtilities utils;

    @Autowired
    private ProductEventProducer productEventProducer;

    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        LOGGER.trace("Creating product");

        ProductResponse productResponse;
        try {
            // convert user request to user object
            Product product = productConverter.convertToProductEntity(createProductRequest);

            // push to db and return db object
            Product productEntity = productRepository.save(product); // this will throw an exception

            // convert entity to response
            productResponse = productConverter.convertToProductResponse(productEntity);

            // emit event
            productEventProducer.publishProductCreatedEvent(productResponse);
            
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

        LOGGER.trace("Created product");
        return productResponse;
    }

    public List<ProductResponse> getProducts(GetProductsRequest getProductsRequest) {
        LOGGER.trace("Retrieving products (get)");

        List<ProductResponse> productResponses;
        try {
            // get object from db
            List<Product> productEntities = productRepository.findByProductIdIn(getProductsRequest.getProductIds()).orElseThrow(EntityNotFoundException::new);

            // convert entity to response
            productResponses = productConverter.convertToProductResponseList(productEntities);

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
        LOGGER.trace("Retrieved products (get)");
        return productResponses;
    }

    public List<ProductResponse> updateProducts(List<UpdateProductRequest> updateProductRequestList) {
        LOGGER.trace("Updating products");

        List<ProductResponse> productResponses;
        try {
            // collect user ids
            List<Integer> productIds = new ArrayList<>();
            for (UpdateProductRequest updateProductRequest : updateProductRequestList) {
                productIds.add(updateProductRequest.getProductId());
            }

            // get user entities
            List<Product> products = productRepository.findByProductIdIn(productIds).orElseThrow(RuntimeException::new);
            // update user entities
            for (int i = 0; i < products.size() - 1; i++) {
                // category
                if (null != updateProductRequestList.get(i).getCategoryId()) {
                    products.get(i).setCategoryId(updateProductRequestList.get(i).getCategoryId());
                }
                // name
                if (!utils.isBlank(updateProductRequestList.get(i).getName())) {
                    products.get(i).setName(updateProductRequestList.get(i).getName());
                }
                // description
                if (!utils.isBlank(updateProductRequestList.get(i).getDescription())) {
                    products.get(i).setDescription(updateProductRequestList.get(i).getDescription());
                }

                products.get(i).setUpdatedTimestamp(OffsetDateTime.now());
            }

            // push to db and return db object
            List<Product> productEntities = productRepository.saveAll(products); // this will throw an exception

            // convert entity to response
            productResponses = productConverter.convertToProductResponseList(productEntities);

            // emit event(s)
            for (ProductResponse productResponseForEvent: productResponses) {
                productEventProducer.publishProductUpdatedEvent(productResponseForEvent);
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
        LOGGER.trace("Updated products");
        return productResponses;
    }

    public ProductResponse deleteProduct(Integer productId) {
        LOGGER.trace("Deleting product: {}", productId);

        ProductResponse productResponse;
        try {
            // delete product
            Product product = productRepository.findById(productId)
                    .orElseThrow(EntityNotFoundException::new);
            productRepository.delete(product);

            // convert entity to response
            productResponse = productConverter.convertToProductResponse(product);

            // emit event
            productEventProducer.publishProductCreatedEvent(productResponse);
            
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
        LOGGER.trace("Deleted product: {}", productId);
        return productResponse;
    }

}
