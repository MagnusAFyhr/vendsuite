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
public class CreateMachineRequest {

    private Integer lockId;

    private Integer productMapId;

    @NotNull
    private Integer telemetryId;

    @NotNull
    private Integer tenantId;

    private Integer screenId;

    private Integer siteId;

    private String machineName;

    private Boolean isConnected;

    @NotNull
    private Integer width;

    @NotNull
    private Integer height;

    private OffsetDateTime operationStartTime;

    private OffsetDateTime operationEndTime;
}
