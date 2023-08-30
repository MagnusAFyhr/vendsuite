package com.packapuff.inventory.service.dto.category;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer categoryId;
    private String name;
    private String description;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
