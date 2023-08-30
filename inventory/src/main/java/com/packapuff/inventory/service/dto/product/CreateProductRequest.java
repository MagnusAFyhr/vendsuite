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
public class CreateProductRequest {

    @NotNull
    private Integer categoryId;

    @NotNull
    private String name;

    @NotNull
    private String description;

}
