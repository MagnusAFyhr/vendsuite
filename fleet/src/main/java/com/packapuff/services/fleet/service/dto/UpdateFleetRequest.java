package com.packapuff.services.fleet.service.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class UpdateFleetRequest {

    @NotNull
    private Integer fleetId;

    private Integer regionSiteId;
    private String fleetName;
    private String description;

}