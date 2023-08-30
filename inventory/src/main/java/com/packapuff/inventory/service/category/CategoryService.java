package com.packapuff.inventory.service.category;

import com.packapuff.inventory.service.dto.category.CategoryResponse;
import com.packapuff.inventory.service.dto.category.CreateCategoryRequest;
import com.packapuff.inventory.service.dto.category.GetCategoriesRequest;
import com.packapuff.inventory.service.dto.category.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {


    CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);

    List<CategoryResponse> getCategories(GetCategoriesRequest getCategoriesRequest);

    List<CategoryResponse> updateCategories(List<UpdateCategoryRequest> updateCategoryRequestList);

    CategoryResponse deleteCategory(Integer categoryId);

}
