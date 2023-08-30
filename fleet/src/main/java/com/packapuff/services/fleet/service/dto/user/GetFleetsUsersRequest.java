package com.packapuff.services.fleet.service.dto.user;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFleetsUsersRequest {

    List<Integer> fleetIds;

}
