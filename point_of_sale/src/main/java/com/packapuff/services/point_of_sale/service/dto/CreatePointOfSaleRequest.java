package com.packapuff.services.point_of_sale.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class CreatePointOfSaleRequest {

    @NotNull
    private Integer telemetryId;

    @NotNull
    private Integer machineId;

    @NotNull
    private String mdbCode;

    @NotNull
    private Integer productId;

    @NotNull
    private float saleAmount;

    @NotNull
    private OffsetDateTime saleTimestamp;

}
