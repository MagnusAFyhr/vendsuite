package com.packapuff.inventory.service.dto.product_activity;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductActivityRequest {

    @NotNull
    private String type;

    @NotNull
    private Integer productId;

    @NotNull
    private Integer productAmount;

    private Integer parentProductActivityId;

    @NotNull
    private String sourceEntityType;

    @NotNull
    private Integer sourceEntityId;
}
