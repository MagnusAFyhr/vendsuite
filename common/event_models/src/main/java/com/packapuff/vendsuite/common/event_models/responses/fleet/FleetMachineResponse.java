package com.packapuff.vendsuite.common.event_models.responses.fleet;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FleetMachineResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer fleetMachineAssocId;
    private Integer fleetId;
    private Integer machineId;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
