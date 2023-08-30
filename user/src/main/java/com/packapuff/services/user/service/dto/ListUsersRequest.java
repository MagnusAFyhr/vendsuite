package com.packapuff.services.user.service.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListUsersRequest {

    private Integer roleId;

}
