package com.packapuff.vendsuite.common.event_models.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class NayaxPointOfSalePayload {

    @NotNull
    OffsetDateTime machineAuTime;

    @NotNull
    Integer deviceNumber;

    @NotNull
    String productPaCode;

    @NotNull
    Float seValue;

}
