package com.packapuff.inventory.service.entity;

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
@Table(name = "product_activity") // unique constraints fo one to many mapping (TFMFleet.java)
public class ProductActivity implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "product_activity_id_sequence")
    @SequenceGenerator(name = "product_activity_id_sequence")
    @Column(name = "product_activity_id", updatable = false)
    private Integer productActivityId;

    /**
     *
     */
    @Column(name = "product_activity_type")
    private String productActivityType;

    /**
     *
     */
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    /**
     *
     */
    @Column(name = "product_amount", nullable = false)
    private Integer productAmount;

    /**
     *
     */
    @Column(name = "parent_product_activity_id", nullable = false)
    private Integer parentProductActivityId;

    /**
     *
     */
    @Column(name = "source_entity_type", nullable = false)
    private String sourceEntityType;

    /**
     *
     */
    @Column(name = "source_entity_id", nullable = false)
    private Integer sourceEntityId;

    /**
     * created timestamp
     */
    @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP WITH TIMEZONE", nullable = false, updatable = false)
    private OffsetDateTime createdTimestamp;

    /**
     * updated timestamp
     */
    @Column(name = "updated_timestamp", columnDefinition = "TIMESTAMP WITH TIMEZONE", nullable = false)
    private OffsetDateTime updatedTimestamp;

}

