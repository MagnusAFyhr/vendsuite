package com.packapuff.inventory.service.dto.product_map;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductMapRequest {

    @NotNull
    private String productMapName;

    @NotNull
    private Integer width;

    @NotNull
    private Integer height;


}
