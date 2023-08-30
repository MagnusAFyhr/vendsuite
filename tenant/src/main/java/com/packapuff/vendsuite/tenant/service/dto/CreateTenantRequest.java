package com.packapuff.vendsuite.tenant.service.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class CreateTenantRequest {

    @NotNull
    private String tenantName;

}
