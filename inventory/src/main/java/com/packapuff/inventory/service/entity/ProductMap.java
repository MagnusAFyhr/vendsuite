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
@Table(name = "product_map")
public class ProductMap implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "product_map_id_sequence")
    @SequenceGenerator(name = "product_map_id_sequence")
    @Column(name = "product_map_id", updatable = false)
    private Integer productMapId;

    /**
     *
     */
    @Column(name = "product_map_name", nullable = false)
    private String productMapName;

    /**
     *
     */
    @Column(name = "width", nullable = false)
    private Integer width;

    /**
     *
     */
    @Column(name = "height", nullable = false)
    private Integer height;

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
