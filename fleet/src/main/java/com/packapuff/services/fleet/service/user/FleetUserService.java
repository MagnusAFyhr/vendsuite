package com.packapuff.services.fleet.service.user;

import com.packapuff.services.fleet.service.dto.user.CreateFleetUserRequest;
import com.packapuff.services.fleet.service.dto.user.FleetUserResponse;
import com.packapuff.services.fleet.service.dto.user.GetFleetsUsersRequest;

import java.util.List;

public interface FleetUserService {

    FleetUserResponse createFleetUser(CreateFleetUserRequest createFleetUserRequest);

    List<FleetUserResponse> getFleetsUsers(GetFleetsUsersRequest getFleetsUsersRequest);

    FleetUserResponse deleteFleetUser(Integer fleetId, Integer userId);

    List<FleetUserResponse> deleteUserFromAllFleets(Integer userId);

    List<FleetUserResponse> deleteAllFleetUsersInFleet(Integer fleetId);

}
