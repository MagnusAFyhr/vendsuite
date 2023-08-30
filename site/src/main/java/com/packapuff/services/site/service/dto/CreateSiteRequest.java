package com.packapuff.services.site.service.dto;

import jakarta.persistence.Column;
import org.jetbrains.annotations.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@AllArgsConstructor
public class CreateSiteRequest {

    @NotNull
    private String siteName;

    @NotNull
    private String siteType;

    @NotNull
    private Integer siteOwnerId;

    @NotNull
    private String streetAddress;

    @NotNull
    private String zipcode;

    @NotNull
    private String city;

    @NotNull
    private String stateCode;

}
