package com.packapuff.services.fleet.service.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFleetsRequest {

    List<Integer> fleetIds;

}
