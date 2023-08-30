package com.packapuff.services.machine.service.repository;


import com.packapuff.services.machine.service.entity.VendingColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendingColumnRepository extends JpaRepository<VendingColumn, Integer> {

    Optional<List<VendingColumn>> findByMachineIdIn(List<Integer> machineIds);

    Optional<List<VendingColumn>> findByMachineId(Integer machineId);

    Optional<VendingColumn> findByMachineIdAndMdbCode(Integer machineId, String mdbCode);

}
