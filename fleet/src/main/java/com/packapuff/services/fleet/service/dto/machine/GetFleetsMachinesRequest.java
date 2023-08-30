package com.packapuff.services.fleet.service.dto.machine;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFleetsMachinesRequest {

    List<Integer> fleetIds;

}
