package com.packapuff.services.machine.service.converter;

import com.packapuff.services.machine.service.dto.column.CreateVendingColumnRequest;
import com.packapuff.services.machine.service.dto.column.VendingColumnResponse;
import com.packapuff.services.machine.service.entity.VendingColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class VendingColumnConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendingColumnConverter.class);

    public VendingColumn convertToVendingColumnEntity(CreateVendingColumnRequest request) {
        LOGGER.trace("Entering VendingColumnConverter::convertToVendingColumnEntity");

        VendingColumn column = new VendingColumn();

        column.setMachineId(request.getMachineId());
        column.setMdbCode(request.getMdbCode());
        column.setProductId(request.getProductId());
        column.setMaxQuantity(request.getMaxQuantity());
        column.setLiveQuantity(request.getLiveQuantity());

        column.setCreatedTimestamp(OffsetDateTime.now());
        column.setUpdatedTimestamp(OffsetDateTime.now());

        LOGGER.trace("Exiting VendingColumnConverter::convertToVendingColumnEntity");
        return column;
    }

    public VendingColumnResponse convertToVendingColumnResponse(VendingColumn column) {
        LOGGER.debug("Entering VendingColumnConverter::convertToVendingColumnResponse");

        VendingColumnResponse response = new VendingColumnResponse();

        response.setVendingColumnId(column.getVendingColumnId());
        response.setMachineId(column.getMachineId());
        response.setMdbCode(column.getMdbCode());
        response.setProductId(column.getProductId());
        response.setMaxQuantity(column.getMaxQuantity());
        response.setLiveQuantity(column.getLiveQuantity());

        response.setCreatedTimestamp(column.getCreatedTimestamp());
        response.setUpdatedTimestamp(column.getUpdatedTimestamp());

        LOGGER.debug("Exiting VendingColumnConverter::convertToVendingColumnResponse");
        return response;
    }

    public List<VendingColumnResponse> convertToVendingColumnResponseList(List<VendingColumn> columnList) {
        LOGGER.trace("Entering VendingColumnConverter::convertToVendingColumnResponseList");

        List<VendingColumnResponse> responseList = new ArrayList<>();

        for (VendingColumn column: columnList) {
            responseList.add(convertToVendingColumnResponse(column));
        }

        LOGGER.trace("Exiting VendingColumnConverter::convertToVendingColumnResponseList");
        return responseList;
    }
}
