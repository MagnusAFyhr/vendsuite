package com.packapuff.services.site.service.entity;

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
@Table(name = "site")
public class Site implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "site_id_sequence")
    @SequenceGenerator(name = "site_id_sequence")
    @Column(name = "site_id")
    private Integer siteId;

    /**
     * name of site
     */
    @Column(name = "site_name", nullable = false)
    private String siteName;

    /**
     * site type
     */
    @Column(name = "site_type", nullable = false)
    private String siteType;

    /**
     * unique identifier of site's owner
     */
    @Column(name = "site_owner_id", nullable = false)
    private Integer siteOwnerId;

    /**
     * street address
     */
    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    /**
     * zipcode
     */
    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    /**
     * city
     */
    @Column(name = "city", nullable = false)
    private String city;

    /**
     * state code
     */
    @Column(name = "state_code", nullable = false)
    private String stateCode;

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
