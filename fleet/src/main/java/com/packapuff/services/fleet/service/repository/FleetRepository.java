package com.packapuff.services.fleet.service.repository;


import com.packapuff.services.fleet.service.entity.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FleetRepository extends JpaRepository<Fleet, Integer> {

    Optional<List<Fleet>> findByFleetIdIn(List<Integer> fleetIds);

}
