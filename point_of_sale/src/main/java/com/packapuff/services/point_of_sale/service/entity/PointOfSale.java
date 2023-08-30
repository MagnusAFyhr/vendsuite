package com.packapuff.services.point_of_sale.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "point_of_sale") // unique constraints fo one to many mapping (TFMFleet.java)
public class PointOfSale implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "point_of_sale_id_sequence")
    @SequenceGenerator(name = "point_of_sale_id_sequence")
    @Column(name = "point_of_sale_id", updatable = false)
    private Integer pointOfSaleId;

    /**
     * identifier for telemetry device where purchase was made
     */
    @Column(name = "telemetry_id", nullable = false, updatable = false)
    private Integer telemetryId;

    /**
     * identifier for machine where purchase was made
     */
    @Column(name = "machine_id", nullable = false, updatable = false)
    private Integer machineId;

    /**
     * MDB code
     */
    @Column(name = "mdb_code", nullable = false, updatable = false)
    private String mdbCode;

    /**
     * product id
     */
    @Column(name = "product_id", nullable = false, updatable = false)
    private Integer productId;

    /**
     * sale amount
     */
    @Column(name = "sale_amount", nullable = false, updatable = false)
    private float saleAmount;

    /**
     * purchase timestamp
     */
    @Column(name = "sale_timestamp", columnDefinition = "TIMESTAMP WITH TIMEZONE", nullable = false, updatable = false)
    private OffsetDateTime saleTimestamp;

    /**
     * created timestamp
     */
    @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP WITH TIMEZONE", nullable = false, updatable = false)
    private OffsetDateTime createdTimestamp;

}
