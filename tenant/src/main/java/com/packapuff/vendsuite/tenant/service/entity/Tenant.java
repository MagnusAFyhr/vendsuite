package com.packapuff.vendsuite.tenant.service.entity;

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
@Table(name = "tenant")
public class Tenant implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "tenant_id_sequence")
    @SequenceGenerator(name = "tenant_id_sequence")
    @Column(name = "tenant_id")
    private Integer tenantId;

    /**
     * name of tenant
     */
    @Column(name = "tenant_name", nullable = false)
    private String tenantName;

    /**
     * created timestamp
     */
    @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP WITH TIMEZONE", nullable = false)
    private OffsetDateTime createdTimestamp;

    /**
     * updated timestamp
     */
    @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP WITH TIMEZONE", nullable = false)
    private OffsetDateTime updatedTimestamp;

}
