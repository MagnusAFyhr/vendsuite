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
public class UpdateProductActivityRequest {

    @NotNull
    private Integer productActivityId;

    private String productActivityType;
    private Integer productId;
    private Integer productAmount;
    private Integer parentProductActivityId;
    private String sourceEntityType;
    private Integer sourceEntityId;

}