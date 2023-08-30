package com.packapuff.services.machine.service.dto.column;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendingColumnResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer vendingColumnId;
    private Integer machineId;
    private String mdbCode;
    private Integer productId;
    private Integer maxQuantity;
    private Integer liveQuantity;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
