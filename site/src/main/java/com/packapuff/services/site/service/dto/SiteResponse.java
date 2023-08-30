package com.packapuff.services.site.service.dto;

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
public class SiteResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer siteId;
    private String siteName;
    private String siteType;
    private Integer siteOwnerId;
    private String streetAddress;
    private String zipcode;
    private String city;
    private String stateCode;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
