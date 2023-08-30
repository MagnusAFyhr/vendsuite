package com.packapuff.inventory.service.product_map;

import com.packapuff.inventory.service.dto.product_map.CreateProductMapRequest;
import com.packapuff.inventory.service.dto.product_map.GetProductMapsRequest;
import com.packapuff.inventory.service.dto.product_map.ProductMapResponse;
import com.packapuff.inventory.service.dto.product_map.UpdateProductMapRequest;

import java.util.List;

public interface ProductMapService {


    ProductMapResponse createProductMap(CreateProductMapRequest createProductMapRequest);

    List<ProductMapResponse> getProductMaps(GetProductMapsRequest getProductMapsRequest);

    List<ProductMapResponse> updateProductMaps(List<UpdateProductMapRequest> updateProductMapRequestList);

    ProductMapResponse deleteProductMap(Integer productMapId);

}
