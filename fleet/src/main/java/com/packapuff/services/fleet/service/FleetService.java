package com.packapuff.services.fleet.service;

import com.packapuff.services.fleet.service.dto.*;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetResponse;

import java.util.List;

public interface FleetService {


    FleetResponse createFleet(CreateFleetRequest createFleetRequest);

    List<FleetResponse> getFleets(GetFleetsRequest getFleetsRequest);

    List<FleetResponse> updateFleets(List<UpdateFleetRequest> updateFleetRequestList);

    FleetResponse deleteFleet(Integer fleetId);

}
