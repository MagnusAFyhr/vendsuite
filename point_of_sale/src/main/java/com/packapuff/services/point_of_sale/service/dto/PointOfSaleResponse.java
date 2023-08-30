package com.packapuff.services.point_of_sale.service.dto;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointOfSaleResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer pointOfSaleId;
    private Integer telemetryId;
    private Integer machineId;
    private String mdbCode;
    private Integer productId;
    private float saleAmount;
    private OffsetDateTime saleTimestamp;
    private OffsetDateTime createdTimestamp;

}
