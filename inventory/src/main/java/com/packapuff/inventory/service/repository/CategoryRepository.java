package com.packapuff.inventory.service.repository;


import com.packapuff.inventory.service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<List<Category>> findByCategoryIdIn(List<Integer> categoryIds);

}
