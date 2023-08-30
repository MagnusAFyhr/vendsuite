package com.packapuff.services.machine.service.event;

import com.packapuff.services.machine.service.MachineService;
import com.packapuff.services.machine.service.dto.CreateMachineRequest;
import com.packapuff.services.machine.service.dto.MachineResponse;
import com.packapuff.services.machine.service.dto.UpdateMachineRequest;
import com.packapuff.vendsuite.common.event_models.model.MachineConnectionEvent;
import com.packapuff.vendsuite.common.event_models.model.MachineRegistrationEvent;
import com.packapuff.vendsuite.common.event_models.payloads.MachineRegistrationPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.ArrayList;
import java.util.List;

public class MachineRegistrationEventConsumer implements ApplicationListener<MachineRegistrationEvent> {

    @Autowired
    MachineService machineService;

    @Override
    public void onApplicationEvent(MachineRegistrationEvent event) {
        System.out.println("Received MachineRegistrationEvent from MachineRegistrationEventConsumer::onApplicationEvent");
        try {
            MachineRegistrationPayload machineRegistrationPayload = (MachineRegistrationPayload) event.getSource();

            MachineResponse machineResponse = createMachineFromMachineRegistrationPayload(machineRegistrationPayload);

        } catch(Exception e) {
            System.out.println("Received bad MachineRegistrationEvent");
        }
        System.out.println("Received MachineRegistrationEvent - " + event.getSource().toString());
    }

    private MachineResponse createMachineFromMachineRegistrationPayload(MachineRegistrationPayload machineRegistrationPayload) {
        CreateMachineRequest createMachineRequest =
                CreateMachineRequest.builder()
                        .machineName(machineRegistrationPayload.getMachineName())
                        .tenantId(machineRegistrationPayload.getTenantId())
                        .telemetryId(machineRegistrationPayload.getTelemetryId())
                        .height(machineRegistrationPayload.getHeight())
                        .width(machineRegistrationPayload.getWidth()).build();

        return machineService.createMachine(createMachineRequest);
    }

}
