package com.packapuff.services.fleet.service.repository;


import com.packapuff.services.fleet.service.entity.FleetMachine;
import com.packapuff.services.fleet.service.entity.FleetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FleetMachineRepository extends JpaRepository<FleetMachine, Integer> {

    Optional<List<FleetMachine>> findByFleetIdIn(List<Integer> fleetIds);
    Optional<FleetMachine> findByMachineId(Integer machineId);
    Optional<List<FleetMachine>> findByFleetId(Integer fleetId);
}
