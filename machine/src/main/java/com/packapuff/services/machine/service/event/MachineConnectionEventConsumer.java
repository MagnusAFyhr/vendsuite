package com.packapuff.services.machine.service.event;

import com.packapuff.services.machine.service.MachineService;
import com.packapuff.services.machine.service.dto.UpdateMachineRequest;
import com.packapuff.services.machine.service.entity.Machine;
import com.packapuff.vendsuite.common.event_models.model.MachineConnectionEvent;
import com.packapuff.vendsuite.common.event_models.payloads.MachineConnectionPayload;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MachineConnectionEventConsumer implements ApplicationListener<MachineConnectionEvent> {

    @Autowired
    MachineService machineService;

    @Override
    public void onApplicationEvent(MachineConnectionEvent event) {
        System.out.println("Received MachineConnectionEvent from MachineConnectionEventConsumer::onApplicationEvent");
        try {
            MachineConnectionPayload payload = (MachineConnectionPayload) event.getSource();

            updateIsConnectedFromUpdateMachineRequest(payload.getMachineId(), payload.getIsConnected());

        } catch(Exception e) {
            System.out.println("Received bad MachineConnectionEvent");
        }
        System.out.println("Received MachineConnectionEvent - " + event.getSource().toString());
    }

    private void updateIsConnectedFromUpdateMachineRequest(Integer machineId, Boolean isConnected) {
        UpdateMachineRequest exclusiveRequest = new UpdateMachineRequest();
        exclusiveRequest.setMachineId(machineId);
        exclusiveRequest.setIsConnected(isConnected);

        List<UpdateMachineRequest> requestList = new ArrayList<>();
        requestList.add(exclusiveRequest);

        machineService.updateMachines(requestList);
    }
}
