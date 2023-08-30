package com.packapuff.services.machine.service.repository;



import com.packapuff.services.machine.service.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {

    Optional<List<Machine>> findByMachineIdIn(List<Integer> machineIds);

    Machine findByTelemetryId(Integer telemetryId);

}
