package com.packapuff.vendsuite.tenant.service.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class UpdateTenantRequest {

    @NotNull
    private Integer tenantId;
    private String tenantName;
}