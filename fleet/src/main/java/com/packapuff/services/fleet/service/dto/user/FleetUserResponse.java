package com.packapuff.services.fleet.service.dto.user;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FleetUserResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer fleetUserAssocId;
    private Integer fleetId;
    private Integer userId;
    private String fleetRole;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
