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
public class CreateVendingColumnRequest {

    @NotNull
    private Integer machineId;

    @NotNull
    private String mdbCode;

    @NotNull
    private Integer productId;

    @NotNull
    private Integer maxQuantity;

    @NotNull
    private Integer liveQuantity;

}
