package com.packapuff.inventory.service.product;

import com.packapuff.inventory.service.dto.product.GetProductsRequest;
import com.packapuff.inventory.service.dto.product.ProductResponse;
import com.packapuff.inventory.service.dto.product.CreateProductRequest;
import com.packapuff.inventory.service.dto.product.UpdateProductRequest;

import java.util.List;

public interface ProductService {


    ProductResponse createProduct(CreateProductRequest createProductRequest);

    List<ProductResponse> getProducts(GetProductsRequest getProductsRequest);

    List<ProductResponse> updateProducts(List<UpdateProductRequest> updateProductRequestList);

    ProductResponse deleteProduct(Integer productId);

}
