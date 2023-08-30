package com.packapuff.services.machine.service.entity;

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
@Table(name = "machine") // unique constraints fo one to many mapping (TFMFleet.java)
public class Machine implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "machine_id_sequence")
    @SequenceGenerator(name = "machine_id_sequence")
    @Column(name = "machine_id", updatable = false)
    private Integer machineId;

    /**
     *
     */
    @Column(name = "lock_id", nullable = true)
    private Integer lockId;

    /**
     *
     */
    @Column(name = "product_map_id", nullable = false)
    private Integer productMapId;

    /**
     *
     */
    @Column(name = "telemetry_id", nullable = false)
    private Integer telemetryId;

    /**
     *
     */
    @Column(name = "tenant_id", nullable = false, updatable = false)
    private Integer tenantId;

    /**
     *
     */
    @Column(name = "screen_id")
    private Integer screenId;

    /**
     *
     */
    @Column(name = "site_id")
    private Integer siteId;

    /**
     * nickname
     */
    @Column(name = "machine_name")
    private String machineName;

    /**
     * connection status
     */
    @Column(name = "is_connected", nullable = false)
    private Boolean isConnected;

    /**
     * number of vending vertical columns
     */
    @Column(name = "width", nullable = false, updatable = false)
    private Integer width;

    /**
     * number of vending horizontal rows
     */
    @Column(name = "height", nullable = false, updatable = false)
    private Integer height;

    /**
     * time machine can start sales
     */
    @Column(name = "operation_start_time")
    private OffsetDateTime operationStartTime;

    /**
     * time machine must end sales
     */
    @Column(name = "operation_end_time")
    private OffsetDateTime operationEndTime;

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
