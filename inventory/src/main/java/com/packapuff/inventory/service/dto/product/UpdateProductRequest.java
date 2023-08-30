package com.packapuff.inventory.service.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    @NotNull
    private Integer productId;

    private Integer categoryId;
    private String name;
    private String description;

}