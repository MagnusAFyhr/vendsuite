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
@Table(name = "product_map_item")
public class ProductMapItem implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "product_map_item_id_sequence")
    @SequenceGenerator(name = "product_map_item_id_sequence")
    @Column(name = "product_map_item_id", updatable = false)
    private Integer productMapItemId;

    /**
     *
     */
    @Column(name = "product_map_id", nullable = false, updatable = false)
    private Integer productMapId;

    /**
     *
     */
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    /**
     *
     */
    @Column(name = "mdb_code", nullable = false, updatable = false)
    private Integer mdbCode;

    /**
     *
     */
    @Column(name = "map_x", nullable = false, updatable = false)
    private Integer mapX;

    /**
     *
     */
    @Column(name = "map_y", nullable = false, updatable = false)
    private Integer mapY;

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
