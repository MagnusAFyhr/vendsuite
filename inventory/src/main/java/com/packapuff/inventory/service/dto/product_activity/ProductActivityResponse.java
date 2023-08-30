package com.packapuff.inventory.service.dto.product_activity;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductActivityResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer productActivityId;
    private String productActivityType;
    private Integer productId;
    private Integer productAmount;
    private Integer parentProductActivityId;
    private String sourceEntityType;
    private Integer sourceEntityId;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
