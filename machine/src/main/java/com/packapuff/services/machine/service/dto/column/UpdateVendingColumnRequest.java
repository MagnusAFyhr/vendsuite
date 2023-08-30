package com.packapuff.services.machine.service.dto.column;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVendingColumnRequest {

    @NotNull
    private Integer vendingColumnId;

    private Integer productId;

    private Integer liveQuantity;

}