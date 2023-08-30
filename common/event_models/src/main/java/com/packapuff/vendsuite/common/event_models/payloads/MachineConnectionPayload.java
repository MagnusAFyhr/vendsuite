package com.packapuff.vendsuite.common.event_models.payloads;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class MachineConnectionPayload implements Serializable {

    @NotNull
    Integer machineId;

    @NotNull
    Boolean isConnected;

    @NotNull
    OffsetDateTime connectionTime;

}
