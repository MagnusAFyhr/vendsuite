package com.packapuff.vendsuite.tenant.service.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer tenantId;
    private String tenantName;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
