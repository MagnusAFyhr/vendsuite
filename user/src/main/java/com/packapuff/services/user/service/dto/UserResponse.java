package com.packapuff.services.user.service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionId = 1L;
    private Integer userId;
    private Integer tenantId;
    private Integer roleId;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

}
