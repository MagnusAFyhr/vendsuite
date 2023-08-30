package com.packapuff.services.fleet.service.dto;

import org.jetbrains.annotations.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class CreateFleetRequest {


    private Integer warehouseId;

    private Integer regionSiteId;

    @NotNull
    private String fleetName;

    private String description;

}
