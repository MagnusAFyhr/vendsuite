package com.packapuff.vendsuite.tenant.service.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTenantsRequest {

    List<Integer> tenantIds;

}
