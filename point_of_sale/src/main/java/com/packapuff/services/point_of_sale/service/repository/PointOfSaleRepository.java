package com.packapuff.services.point_of_sale.service.repository;



import com.packapuff.services.point_of_sale.service.entity.PointOfSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PointOfSaleRepository extends JpaRepository<PointOfSale, Integer> {

    Optional<PointOfSale> findByPointOfSaleId(Integer pointOfSaleId);

    Optional<List<PointOfSale>> findPointOfSalesByTelemetryId(UUID telemetryId);

    Optional<List<PointOfSale>> findPointOfSalesByProductId(Integer productId);

    Optional<List<PointOfSale>> findPointOfSalesByTelemetryIdAndProductId(UUID telemetryID, Integer productId);

}
