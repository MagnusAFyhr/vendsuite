package com.packapuff.inventory.service.repository;

import com.packapuff.inventory.service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<List<Product>> findByProductIdIn(List<Integer> productIds);

}
