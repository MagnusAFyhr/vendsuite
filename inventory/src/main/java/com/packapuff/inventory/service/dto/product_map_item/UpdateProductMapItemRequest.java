package com.packapuff.inventory.service.dto.product_map_item;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class UpdateProductMapItemRequest {

    @NotNull
    private Integer productMapItemId;

    private Integer productMapId;
    private Integer productId;
    private Integer mdbCode;

    private Integer mapX;
    private Integer mapY;

}