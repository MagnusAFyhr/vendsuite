package com.packapuff.services.machine.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMachineRequest {

    @NotNull
    private Integer machineId;

    private Integer lockId;
    private Integer productMapId;
    private Integer telemetryId;
    private Integer screenId;
    private Integer siteId;
    private String machineName;
    private Boolean isConnected;
    private OffsetDateTime operationStartTime;
    private OffsetDateTime operationEndTime;

}