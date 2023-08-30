package com.packapuff.services.fleet.service.dto.machine;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class CreateFleetMachineRequest {

    @NotNull
    private Integer fleetId;

    @NotNull
    private Integer machineId;

}
