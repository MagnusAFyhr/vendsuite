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
@IdClass(FleetUser.class)
@Table(name = "fleet_user") // unique constraints fo one to many mapping (TFMFleet.java)
public class FleetUser implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "fleet_user_assoc_id_sequence")
    @SequenceGenerator(name = "fleet_user_assoc_id_sequence")
    @Column(name = "fleet_user_assoc_id", updatable = false)
    private Integer fleetUserAssocId;

    /**
     * Fleet identifier
     */
    @Id
    @Column(name = "fleet_id", nullable = false, updatable = false)
    private Integer fleetId;

    /**
     * User identifier
     */
    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private Integer userId;

    /**
     * Role within fleet
     */
    @Column(name = "fleet_role", nullable = false)
    private String fleetRole;

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
