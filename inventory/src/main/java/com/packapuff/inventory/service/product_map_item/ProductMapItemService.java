package com.packapuff.inventory.service.product_map_item;

import com.packapuff.inventory.service.dto.product_map_item.CreateProductMapItemRequest;
import com.packapuff.inventory.service.dto.product_map_item.GetProductMapItemsRequest;
import com.packapuff.inventory.service.dto.product_map_item.ProductMapItemResponse;
import com.packapuff.inventory.service.dto.product_map_item.UpdateProductMapItemRequest;

import java.util.List;

public interface ProductMapItemService {


    List<ProductMapItemResponse> createProductMapItems(List<CreateProductMapItemRequest> createProductMapItemRequestList);

    List<ProductMapItemResponse> getProductMapItems(GetProductMapItemsRequest getProductMapItemsRequest);

    List<ProductMapItemResponse> updateProductMapItems(List<UpdateProductMapItemRequest> updateProductMapItemRequestList);

    List<ProductMapItemResponse> deleteProductMapItems(Integer productMapId);

}
