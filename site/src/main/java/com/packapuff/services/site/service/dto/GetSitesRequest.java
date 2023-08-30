package com.packapuff.services.site.service.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSitesRequest {

    List<Integer> siteIds;

}
