package com.packapuff.inventory.service.product_activity;

import com.packapuff.inventory.service.dto.product_activity.CreateProductActivityRequest;
import com.packapuff.inventory.service.dto.product_activity.GetProductActivitiesRequest;
import com.packapuff.inventory.service.dto.product_activity.ProductActivityResponse;
import com.packapuff.inventory.service.dto.product_activity.UpdateProductActivityRequest;

import java.util.List;

public interface ProductActivityService {


    ProductActivityResponse createProductActivity(CreateProductActivityRequest createProductActivityRequest);

    List<ProductActivityResponse> getProductActivities(GetProductActivitiesRequest getProductActivitiesRequest);

//    List<ProductActivityResponse> updateProductActivities(List<UpdateProductActivityRequest> updateProductActivityRequestList);

    ProductActivityResponse deleteProductActivity(Integer categoryId);

}
