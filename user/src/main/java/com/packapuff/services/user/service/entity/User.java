package com.packapuff.services.user.service.entity;

import com.packapuff.services.user.service.dto.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user`") // unique constraints fo one to many mapping (TFMFleet.java)
public class User implements Serializable {

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "user_id_sequence")
    @SequenceGenerator(name = "user_id_sequence")
    @Column(name = "user_id", updatable = false)
    private Integer userId;

    /**
     * tenant user belongs to
     */
    @Column(name = "tenant_id", nullable = false, updatable = false)
    private Integer tenantId;

    /**
     * System level role of user
     */
    @Column(name = "role_id", nullable = false, updatable = false)
    private Integer roleId;

    /**
     * User's first name
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * User's last name
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * email
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * phone number
     */
    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    /**
     * rfid code
     */
    @Column(name = "rfid")
    private UUID rfid;

    /**
     * created timestamp
     */
    @Column(name = "created_timestamp", nullable = false, updatable = false)
    private OffsetDateTime createdTimestamp;

    /**
     * updated timestamp
     */
    @Column(name = "updated_timestamp", nullable = false)
    private OffsetDateTime updatedTimestamp;

}
