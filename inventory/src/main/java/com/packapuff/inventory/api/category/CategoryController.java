package com.packapuff.inventory.api.category;

import com.packapuff.inventory.service.dto.category.CategoryResponse;
import com.packapuff.inventory.service.dto.category.CreateCategoryRequest;
import com.packapuff.inventory.service.dto.category.GetCategoriesRequest;
import com.packapuff.inventory.service.dto.category.UpdateCategoryRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * [Insert Comments]
 *
 */

@Validated
@RequestMapping("/inventory/category")
public interface CategoryController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<CategoryResponse>> getCategories(@Valid @RequestBody GetCategoriesRequest requestBody);

    @PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<CategoryResponse>> updateCategories(@Valid @RequestBody List<UpdateCategoryRequest> requestBody);

    @DeleteMapping(value = "/{categoryId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<CategoryResponse> deleteCategory(@Valid @PathVariable("categoryId") @NotNull Integer categoryId);

}
