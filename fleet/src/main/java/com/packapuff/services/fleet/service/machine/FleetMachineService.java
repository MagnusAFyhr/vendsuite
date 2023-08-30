package com.packapuff.services.fleet.service.machine;


import com.packapuff.services.fleet.service.dto.machine.CreateFleetMachineRequest;
import com.packapuff.services.fleet.service.dto.machine.FleetMachineResponse;
import com.packapuff.services.fleet.service.dto.machine.GetFleetsMachinesRequest;

import java.util.List;

public interface FleetMachineService {


    FleetMachineResponse createFleetMachine(CreateFleetMachineRequest createFleetMachineRequest);

    List<FleetMachineResponse> getFleetsMachines(GetFleetsMachinesRequest getFleetsMachinesRequest);

    FleetMachineResponse deleteFleetMachine(Integer machineId);

    List<FleetMachineResponse> deleteAllFleetMachinesInFleet(Integer fleetId);

}
