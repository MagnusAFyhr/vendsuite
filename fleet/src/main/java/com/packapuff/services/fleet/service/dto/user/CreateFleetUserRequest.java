package com.packapuff.services.fleet.service.dto.user;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class CreateFleetUserRequest {

    @NotNull
    private Integer fleetId;

    @NotNull
    private Integer userId;

    @NotNull
    private String fleetRole;

}
