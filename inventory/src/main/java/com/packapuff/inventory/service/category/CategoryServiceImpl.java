package com.packapuff.inventory.service.category;

import com.packapuff.inventory.service.event.CategoryEventProducer;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.inventory.service.converter.CategoryConverter;
import com.packapuff.inventory.service.dto.category.CategoryResponse;
import com.packapuff.inventory.service.dto.category.CreateCategoryRequest;
import com.packapuff.inventory.service.dto.category.GetCategoriesRequest;
import com.packapuff.inventory.service.dto.category.UpdateCategoryRequest;
import com.packapuff.inventory.service.entity.Category;
import com.packapuff.inventory.service.repository.CategoryRepository;
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
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private InventoryUtilities utils;

    @Autowired
    private CategoryEventProducer categoryEventProducer;

    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        log.trace("Creating category");

        CategoryResponse categoryResponse;
        try {
            // convert user request to user object
            Category category = categoryConverter.convertToCategoryEntity(createCategoryRequest);

            // push to db and return db object
            Category categoryEntity = categoryRepository.save(category); // this will throw an exception

            // convert entity to response
            categoryResponse = categoryConverter.convertToCategoryResponse(categoryEntity);

            // emit event
            categoryEventProducer.publishCategoryCreatedEvent(categoryResponse);

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

        log.trace("Created category");
        return categoryResponse;
    }

    public List<CategoryResponse> getCategories(GetCategoriesRequest getCategoriesRequest) {
        log.trace("Retrieving categories (get)");

        List<CategoryResponse> categoryResponses;
        try {
            // get object from db
            List<Category> categoryEntities = categoryRepository.findByCategoryIdIn(getCategoriesRequest.getCategoryIds()).orElseThrow(EntityNotFoundException::new);

            // convert entity to response
            categoryResponses = categoryConverter.convertToCategoryResponseList(categoryEntities);
            
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
        log.trace("Retrieved categories (get)");
        return categoryResponses;
    }

    public List<CategoryResponse> updateCategories(List<UpdateCategoryRequest> updateCategoryRequestList) {
        log.trace("Updating categories");

        List<CategoryResponse> categoryResponses;
        try {
            // collect user ids
            List<Integer> categoryIds = new ArrayList<>();
            for (UpdateCategoryRequest updateCategoryRequest : updateCategoryRequestList) {
                categoryIds.add(updateCategoryRequest.getCategoryId());
            }

            // get user entities
            List<Category> categories = categoryRepository.findByCategoryIdIn(categoryIds).orElseThrow(RuntimeException::new);
            // update user entities
            for (int i = 0; i < categories.size() - 1; i++) {
                if (!utils.isBlank(updateCategoryRequestList.get(i).getName())) {
                    categories.get(i).setName(updateCategoryRequestList.get(i).getName());
                }
                if (!utils.isBlank(updateCategoryRequestList.get(i).getDescription())) {
                    categories.get(i).setDescription(updateCategoryRequestList.get(i).getDescription());
                }

                categories.get(i).setUpdatedTimestamp(OffsetDateTime.now());
            }


            // push to db and return db object
            List<Category> categoryEntities = categoryRepository.saveAll(categories); // this will throw an exception

            // convert entity to response
            categoryResponses = categoryConverter.convertToCategoryResponseList(categoryEntities);

            // emit event(s)
            for (CategoryResponse categoryResponseForEvent: categoryResponses) {
                categoryEventProducer.publishCategoryUpdatedEvent(categoryResponseForEvent);
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
        log.trace("Updated categories");
        return categoryResponses;
    }

    public CategoryResponse deleteCategory(Integer categoryId) {
        log.trace("Deleting category: {}", categoryId);

        CategoryResponse categoryResponse;
        try {
            // delete category
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(EntityNotFoundException::new);
            categoryRepository.delete(category);

            // convert entity to response
            categoryResponse = categoryConverter.convertToCategoryResponse(category);
            
            // emit event
            categoryEventProducer.publishCategoryCreatedEvent(categoryResponse);

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
        log.trace("Deleted category: {}", categoryId);
        return categoryResponse;
    }

}
