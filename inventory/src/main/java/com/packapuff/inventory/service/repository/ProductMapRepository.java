package com.packapuff.inventory.service.repository;

import com.packapuff.inventory.service.entity.ProductMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductMapRepository extends JpaRepository<ProductMap, Integer> {

    Optional<List<ProductMap>> findByProductMapIdIn(List<Integer> productMapIds);

}
