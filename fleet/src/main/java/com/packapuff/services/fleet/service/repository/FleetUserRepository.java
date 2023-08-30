package com.packapuff.services.fleet.service.repository;


import com.packapuff.services.fleet.service.entity.Fleet;
import com.packapuff.services.fleet.service.entity.FleetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FleetUserRepository extends JpaRepository<FleetUser, Integer> {

    Optional<List<FleetUser>> findByFleetIdIn(List<Integer> fleetIds);

    List<FleetUser> findAllByUserId(Integer userId);

    Optional<List<FleetUser>> findByUserIdIn(List<Integer> userIds);

    Optional<FleetUser> findByFleetIdAndUserId(Integer fleetId, Integer userId);

    Optional<List<FleetUser>> findByFleetId(Integer fleetId);


}
