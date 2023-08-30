package com.packapuff.services.machine.service.dto;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer machineId;
    private Integer telemetryId;
    private Integer lockId;
    private String machineName;
    private Boolean isConnected;
    private Integer width;
    private Integer height;
    private OffsetDateTime operationStartTime;
    private OffsetDateTime operationEndTime;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
