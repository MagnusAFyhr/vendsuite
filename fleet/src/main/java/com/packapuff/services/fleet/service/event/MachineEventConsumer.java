package com.packapuff.services.fleet.service.event;

import com.packapuff.services.fleet.service.dto.machine.CreateFleetMachineRequest;
import com.packapuff.services.fleet.service.machine.FleetMachineService;
import com.packapuff.vendsuite.common.event_models.enums.CrudEventType;
import com.packapuff.vendsuite.common.event_models.model.MachineEvent;
import com.packapuff.vendsuite.common.event_models.responses.machine.MachineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import static com.packapuff.services.fleet.service.constants.FleetConstants.ZERO_FLEET;

public class MachineEventConsumer implements ApplicationListener<MachineEvent> {

    @Autowired
    FleetMachineService fleetMachineService;

    @Override
    public void onApplicationEvent(MachineEvent event) {
        System.out.println("Received MachineEvent from MachineEventConsumer::onApplicationEvent");
        try {

            // DELETED event type
            if (event.getEventType().equals(CrudEventType.DELETED.getValue())) {
                MachineResponse machineResponse = (MachineResponse) event.getSource();

                // delete fleet machine
                fleetMachineService.deleteFleetMachine(machineResponse.getMachineId());
            }
            // CREATED event type
            else if (event.getEventType().equals(CrudEventType.CREATED.getValue())) {
                MachineResponse machineResponse = (MachineResponse) event.getSource();

                // add machine to "zero" fleet
                CreateFleetMachineRequest createFleetMachineRequest =
                        CreateFleetMachineRequest.builder()
                                .machineId(machineResponse.getMachineId()).fleetId(ZERO_FLEET).build();
                fleetMachineService.createFleetMachine(createFleetMachineRequest);
            }

        } catch(Exception e) {
            System.out.println("Received bad MachineEvent");
        }
        System.out.println("Received MachineEvent - " + event.getSource().toString());
    }

}
