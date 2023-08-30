package com.packapuff.inventory.service.dto.product_map_item;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMapItemResponse implements Serializable {

    private Integer productMapItemId;
    private Integer productMapId;
    private Integer productId;
    private Integer mdbCode;

    private Integer mapX;
    private Integer mapY;

    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
