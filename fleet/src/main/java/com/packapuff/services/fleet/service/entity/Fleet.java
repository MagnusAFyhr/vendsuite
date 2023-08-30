package com.packapuff.services.fleet.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fleet") // unique constraints fo one to many mapping (TFMFleet.java)
public class Fleet implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "fleet_id_sequence")
    @SequenceGenerator(name = "fleet_id_sequence")
    @Column(name = "fleet_id", updatable = false)
    private Integer fleetId;

    /**
     * warehouse
     */
    @Column(name = "warehouse_id")
    private Integer warehouseId;

    /**
     * region which represents a site object
     */
    @Column(name = "region_site_id")
    private Integer regionSiteId;

    /**
     *
     */
    @Column(name = "creator_user_id", nullable = false, updatable = false)
    private Integer creatorUserId;

    /**
     *
     */
    @Column(name = "fleet_name", nullable = false)
    private String fleetName;

    /**
     * User's last name
     */
    @Column(name = "description")
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

    /**
     * create one-to-many relation with fleet_user table
     */
    @OneToMany(mappedBy = "fleet_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FleetUser> fleetUsers;

    /**
     * create one-to-many relation with fleet_user table
     */
    @OneToMany(mappedBy = "fleet_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FleetMachine> fleetMachines;
}
