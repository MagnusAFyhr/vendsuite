package com.packapuff.services.point_of_sale.service;

import com.packapuff.services.point_of_sale.service.dto.CreatePointOfSaleRequest;
import com.packapuff.services.point_of_sale.service.dto.ListPointOfSalesRequest;
import com.packapuff.services.point_of_sale.service.dto.PointOfSaleResponse;

import java.util.List;
import java.util.UUID;

public interface PointOfSaleService {


    PointOfSaleResponse createPointOfSale(CreatePointOfSaleRequest createPointOfSaleRequest);

    List<PointOfSaleResponse> getPointOfSales(List<Integer> pointOfSaleIds);

    List<PointOfSaleResponse> listPointOfSales(ListPointOfSalesRequest listPointOfSalesRequest);

}
