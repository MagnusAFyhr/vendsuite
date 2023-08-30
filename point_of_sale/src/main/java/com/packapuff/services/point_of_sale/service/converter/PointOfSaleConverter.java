package com.packapuff.services.point_of_sale.service.converter;

import com.packapuff.services.point_of_sale.service.dto.CreatePointOfSaleRequest;
import com.packapuff.services.point_of_sale.service.dto.PointOfSaleResponse;
import com.packapuff.services.point_of_sale.service.entity.PointOfSale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PointOfSaleConverter {

    private static final Logger log = LoggerFactory.getLogger(PointOfSaleConverter.class);

    public PointOfSale convertToPointOfSaleEntity(CreatePointOfSaleRequest request) {
        log.trace("Entering convertToPointOfSaleEntity");

        PointOfSale pointOfSale = new PointOfSale();

        pointOfSale.setTelemetryId(request.getTelemetryId());
        pointOfSale.setMachineId(request.getMachineId());
        pointOfSale.setMdbCode(request.getMdbCode());
        pointOfSale.setProductId(request.getProductId());
        pointOfSale.setSaleAmount(request.getSaleAmount());
        pointOfSale.setSaleTimestamp(request.getSaleTimestamp());
        pointOfSale.setCreatedTimestamp(OffsetDateTime.now());

        log.trace("Exiting convertToPointOfSaleEntity");
        return pointOfSale;
    }

    public PointOfSaleResponse convertToPointOfSaleResponse(PointOfSale pointOfSale) {
        log.debug("Entering convertToPointOfSaleResponse");

        PointOfSaleResponse response = new PointOfSaleResponse();

        response.setPointOfSaleId(pointOfSale.getPointOfSaleId());
        response.setTelemetryId(pointOfSale.getTelemetryId());
        response.setMachineId(pointOfSale.getMachineId());
        response.setMdbCode(pointOfSale.getMdbCode());
        response.setProductId(pointOfSale.getProductId());
        response.setSaleAmount(pointOfSale.getSaleAmount());
        response.setSaleTimestamp(pointOfSale.getSaleTimestamp());
        response.setCreatedTimestamp(pointOfSale.getCreatedTimestamp());

        log.debug("Exiting convertToPointOfSaleResponse");
        return response;
    }

    public List<PointOfSaleResponse> convertToPointOfSaleResponseList(List<PointOfSale> pointOfSaleList) {
        log.trace("Entering convertToPointOfSaleResponseList");

        List<PointOfSaleResponse> responseList = new ArrayList<>();

        for (PointOfSale pointOfSale: pointOfSaleList) {
            responseList.add(convertToPointOfSaleResponse(pointOfSale));
        }

        log.trace("Exiting convertToPointOfSaleResponseList");
        return responseList;
    }
}
