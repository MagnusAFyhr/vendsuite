package com.packapuff.services.point_of_sale.service.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListPointOfSalesRequest {

    private Integer machineId;

    private Integer productId;

    private OffsetDateTime startTimestamp;

    private OffsetDateTime endTimestamp;
}
