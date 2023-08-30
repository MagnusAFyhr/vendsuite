package com.packapuff.inventory.service.dto.product_map;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class UpdateProductMapRequest {

    @NotNull
    private Integer productMapId;
    private String productMapName;
    private Integer width;
    private Integer height;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}