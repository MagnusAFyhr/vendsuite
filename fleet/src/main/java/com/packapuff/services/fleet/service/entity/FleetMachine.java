package com.packapuff.services.fleet.service.entity;

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
@IdClass(FleetMachine.class)
@Table(name = "fleet_machine") // unique constraints fo one to many mapping (TFMFleet.java)
public class FleetMachine implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "fleet_machine_assoc_id_sequence")
    @SequenceGenerator(name = "fleet_machine_assoc_id_sequence")
    @Column(name = "fleet_machine_assoc_id", updatable = false)
    private Integer fleetMachineAssocId;

    /**
     * Fleet identifier
     */
    @Column(name = "fleet_id", nullable = false, updatable = false)
    private Integer fleetId;

    /**
     * Machine identifier
     */
    @Column(name = "machine_id", nullable = false, updatable = false)
    private Integer machineId;

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
