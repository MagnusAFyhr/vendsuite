package com.packapuff.inventory.service.repository;

import com.packapuff.inventory.service.entity.ProductMapItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductMapItemRepository extends JpaRepository<ProductMapItem, Integer> {

    Optional<List<ProductMapItem>> findByProductMapIdIn(List<Integer> productMapIds);
    Optional<List<ProductMapItem>> findByProductMapId(Integer productMapId);


}
