package com.packapuff.services.machine.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(VendingColumn.class)
@Table(name = "vending_column") // unique constraints fo one to many mapping (TFMFleet.java)
public class VendingColumn implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "vending_column_id_sequence")
    @SequenceGenerator(name = "vending_column_id_sequence")
    @Column(name = "vending_column_id", updatable = false)
    private Integer vendingColumnId;

    /**
     *
     */
    @Column(name = "machine_id")
    private Integer machineId;


    /**
     * Identifier for product being stocked in this vending column
     */
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    /**
     *
     */
    @Column(name = "mdb_code", nullable = false)
    private String mdbCode;

    /**
     * Vending column's max product quantity
     */
    @Column(name = "max_quantity", nullable = false)
    private Integer maxQuantity;

    /**
     * Vending column's live product quantity
     */
    @Column(name = "live_quantity", nullable = false)
    private Integer liveQuantity;

    /**
     * Created timestamp
     */
    @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP WITH TIMEZONE", nullable = false, updatable = false)
    private OffsetDateTime createdTimestamp;

    /**
     * Updated timestamp
     */
    @Column(name = "updated_timestamp", columnDefinition = "TIMESTAMP WITH TIMEZONE", nullable = false)
    private OffsetDateTime updatedTimestamp;

}
