package com.packapuff.services.fleet.service.dto;


import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FleetResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer fleetId;
    private Integer warehouseId;
    private Integer regionSiteId;
    private Integer creatorUserId;
    private String fleetName;
    private String description;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
