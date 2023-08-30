package com.packapuff.inventory.service.dto.product_map;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMapResponse implements Serializable {

    private Integer productMapId;
    private String productMapName;
    private Integer width;
    private Integer height;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
