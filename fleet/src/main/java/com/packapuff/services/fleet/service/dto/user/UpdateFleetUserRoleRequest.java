package com.packapuff.services.fleet.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class UpdateFleetUserRoleRequest {

    @NotNull
    private Integer fleetUserAssocId;

    private String fleetRole;
}