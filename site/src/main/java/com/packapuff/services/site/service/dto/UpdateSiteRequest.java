package com.packapuff.services.site.service.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class UpdateSiteRequest {

    @NotNull
    private Integer siteId;
    private String siteName;
    private String siteType;
    private Integer siteOwnerId;
    private String streetAddress;
    private String zipcode;
    private String city;
    private String stateCode;

}