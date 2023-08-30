package com.packapuff.services.machine.service;

import com.packapuff.services.machine.service.dto.*;

import java.util.List;

public interface MachineService {


    MachineResponse createMachine(CreateMachineRequest createMachineRequest);

    List<MachineResponse> getMachines(GetMachinesRequest getMachinesRequest);

    MachineResponse getMachineByTelemetryId(Integer telemetryId);

    List<MachineResponse> listMachines(ListMachinesRequest listMachinesRequest);

    List<MachineResponse> updateMachines(List<UpdateMachineRequest> updateMachineRequestList);

    MachineResponse deleteMachine(Integer machineId);

}
