package com.packapuff.services.user.service.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUsersRequest {

    List<Integer> userIds;

}
