package com.packapuff.inventory.service.repository;


import com.packapuff.inventory.service.entity.ProductActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductActivityRepository extends JpaRepository<ProductActivity, Integer> {

    Optional<List<ProductActivity>> findByProductActivityIdIn(List<Integer> activityIds);

}
