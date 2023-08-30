package com.packapuff.services.user.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @NotNull
    private Integer userId;

    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;

}