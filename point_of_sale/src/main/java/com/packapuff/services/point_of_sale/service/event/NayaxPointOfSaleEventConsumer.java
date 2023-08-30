package com.packapuff.services.point_of_sale.service.event;

import com.packapuff.services.machine.api.MachineController;
import com.packapuff.services.machine.api.column.VendingColumnController;
import com.packapuff.services.machine.service.MachineService;
import com.packapuff.services.machine.service.dto.MachineResponse;
import com.packapuff.services.machine.service.dto.UpdateMachineRequest;
import com.packapuff.services.machine.service.dto.column.VendingColumnResponse;
import com.packapuff.services.point_of_sale.service.PointOfSaleService;
import com.packapuff.services.point_of_sale.service.dto.CreatePointOfSaleRequest;
import com.packapuff.vendsuite.common.event_models.model.MachineConnectionEvent;
import com.packapuff.vendsuite.common.event_models.model.NayaxPointOfSaleEvent;
import com.packapuff.vendsuite.common.event_models.payloads.MachineConnectionPayload;
import com.packapuff.vendsuite.common.event_models.payloads.NayaxPointOfSalePayload;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class NayaxPointOfSaleEventConsumer implements ApplicationListener<NayaxPointOfSaleEvent> {

    @Autowired
    PointOfSaleService pointOfSaleService;

    @Autowired
    MachineController machineController;

    @Autowired
    VendingColumnController vendingColumnController;

    @Override
    public void onApplicationEvent(NayaxPointOfSaleEvent event) {
        System.out.println("Received NayaxPointOfSaleEvent from NayaxPointOfSaleEventConsumer::onApplicationEvent");
        try {
            NayaxPointOfSalePayload payload = (NayaxPointOfSalePayload) event.getSource();

            CreatePointOfSaleRequest createPointOfSaleRequest = convertNayaxPointOfSalePayloadToCreatePointOfSaleRequest(payload);

            pointOfSaleService.createPointOfSale(createPointOfSaleRequest);

        } catch(Exception e) {
            System.out.println("Received bad NayaxPointOfSaleEvent");
        }
        System.out.println("Received NayaxPointOfSaleEvent - " + event.getSource().toString());
    }

    private CreatePointOfSaleRequest convertNayaxPointOfSalePayloadToCreatePointOfSaleRequest(
            NayaxPointOfSalePayload nayaxPointOfSalePayload) {

        Integer telemetryId = nayaxPointOfSalePayload.getDeviceNumber();

        MachineResponse machineResponse =  machineController.getMachineByTelemetryId(telemetryId).getBody();
        assert machineResponse != null;

        Integer machineId = machineResponse.getMachineId();
        String mdbCode = nayaxPointOfSalePayload.getProductPaCode();

        VendingColumnResponse vendingColumnResponse =
                vendingColumnController.getVendingColumnByMdbCode(machineId, mdbCode).getBody();
        assert vendingColumnResponse != null;

        Integer productId = vendingColumnResponse.getProductId();
        float saleAmount = nayaxPointOfSalePayload.getSeValue();
        OffsetDateTime saleTimestamp = nayaxPointOfSalePayload.getMachineAuTime();

        return CreatePointOfSaleRequest.builder()
                .telemetryId(telemetryId)
                .machineId(machineId)
                .mdbCode(mdbCode)
                .productId(productId)
                .saleAmount(saleAmount)
                .saleTimestamp(saleTimestamp)
                .build();

    }
}
