package com.packapuff.services.user.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRFIDRequest {

    @NotNull
    private Integer userId;

    @NotNull
    private UUID rfid;
}
