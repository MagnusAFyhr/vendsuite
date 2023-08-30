package com.packapuff.services.machine.service.event;

import com.packapuff.services.machine.service.MachineService;
import com.packapuff.services.machine.service.column.VendingColumnService;
import com.packapuff.services.machine.service.dto.UpdateMachineRequest;
import com.packapuff.services.machine.service.dto.column.GetVendingColumnsRequest;
import com.packapuff.services.machine.service.dto.column.UpdateVendingColumnRequest;
import com.packapuff.services.machine.service.dto.column.VendingColumnResponse;
import com.packapuff.services.machine.service.entity.VendingColumn;
import com.packapuff.services.point_of_sale.service.dto.PointOfSaleResponse;
import com.packapuff.services.point_of_sale.service.entity.PointOfSale;
import com.packapuff.vendsuite.common.event_models.model.PointOfSaleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PointOfSaleEventConsumer implements ApplicationListener<PointOfSaleEvent> {

    @Autowired
    VendingColumnService vendingColumnService;

    @Override
    public void onApplicationEvent(PointOfSaleEvent event) {
        System.out.println("Received PointOfSaleEvent from PointOfSaleEventConsumer::onApplicationEvent");
        try {
            PointOfSaleResponse pointOfSale = (PointOfSaleResponse) event.getSource();

            updateLiveQuantityFromPointOfSaleResponse(pointOfSale);

        } catch(Exception e) {
            System.out.println("Received bad PointOfSaleEvent");
        }
        System.out.println("Received PointOfSaleEvent - " + event.getSource().toString());
    }

    private void updateLiveQuantityFromPointOfSaleResponse(PointOfSaleResponse pointOfSaleResponse) {

        VendingColumnResponse vendingColumnResponse =
                vendingColumnService.getVendingColumnByMdbCode(pointOfSaleResponse.getMachineId(), pointOfSaleResponse.getMdbCode());

        assert(vendingColumnResponse != null);

        UpdateVendingColumnRequest updateVendingColumnRequest =
                UpdateVendingColumnRequest.builder()
                        .vendingColumnId(vendingColumnResponse.getVendingColumnId())
                        .productId(vendingColumnResponse.getProductId())
                        .liveQuantity(vendingColumnResponse.getLiveQuantity() - 1).build();


        List<UpdateVendingColumnRequest> requestList = new ArrayList<>();
        requestList.add(updateVendingColumnRequest);

        vendingColumnService.updateVendingColumns(requestList);
    }

}
