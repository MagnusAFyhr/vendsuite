package com.packapuff.vendsuite.common.event_models.payloads;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class MachineRegistrationPayload implements Serializable {

    @NotNull
    private Integer telemetryId;

    @NotNull
    private String machineName;

    @NotNull
    private Integer tenantId;

    @NotNull
    private Integer width;

    @NotNull
    private Integer height;

}
