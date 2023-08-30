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
@Table(name = "category") // unique constraints fo one to many mapping (TFMFleet.java)
public class Category implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "category_id_sequence")
    @SequenceGenerator(name = "category_id_sequence")
    @Column(name = "category_id", updatable = false)
    private Integer categoryId;

    /**
     *
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     *
     */
    @Column(name = "description", nullable = false)
    private String description;

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

