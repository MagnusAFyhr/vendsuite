package com.packapuff.inventory.api.category;

import com.packapuff.inventory.service.dto.category.CategoryResponse;
import com.packapuff.inventory.service.dto.category.CreateCategoryRequest;
import com.packapuff.inventory.service.dto.category.GetCategoriesRequest;
import com.packapuff.inventory.service.dto.category.UpdateCategoryRequest;
import com.packapuff.inventory.service.category.CategoryService;
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
public class CategoryControllerImpl implements CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryControllerImpl.class);

    @Autowired
    private CategoryService categoryService;

    /**
     *
     * Creates a category and stores in DB
     *
     * @parameter RequestBody CreateCategoryRequest
     *
     */
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
    ) {
        log.trace("Entering createCategory");

        CategoryResponse category = categoryService.createCategory(createCategoryRequest);

        log.trace("Exiting createCategory");
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     *
     * Gets a category from DB
     *
     * @parameter Integer categoryId
     *
     */
    public ResponseEntity<List<CategoryResponse>> getCategories(
            @Valid @RequestBody GetCategoriesRequest getCategoriesRequest
    ) {
        log.trace("Entering getCategory");

        List<CategoryResponse> categories = categoryService.getCategories(getCategoriesRequest);

        log.trace("Exiting getCategory");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     *
     * Update categorys in DB from list
     *
     * @parameter RequestBody List<UpdateCategoryRequest>
     *
     */
    public ResponseEntity<List<CategoryResponse>> updateCategories(
            @Valid @RequestBody List<UpdateCategoryRequest> requestBody
    ) {
        log.trace("Entering updateCategories");

        List<CategoryResponse> categories = categoryService.updateCategories(requestBody);

        log.trace("Exiting updateCategories");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     *
     * Delete category from DB
     *
     * @parameter RequestBody List<UpdateCategoryRequest>
     *
     */
    public ResponseEntity<CategoryResponse> deleteCategory(Integer categoryId) {
        log.trace("Entering deleteCategory");

        CategoryResponse category = categoryService.deleteCategory(categoryId);

        log.trace("Exiting deleteCategory");
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
