package com.packapuff.inventory.service.dto.product_map_item;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class CreateProductMapItemRequest {

    @NotNull
    private Integer productMapId;

    @NotNull
    private Integer productId;

    @NotNull
    private Integer mdbCode;

    @NotNull
    private Integer mapX;

    @NotNull
    private Integer mapY;

}
