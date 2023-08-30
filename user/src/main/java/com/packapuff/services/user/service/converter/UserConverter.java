package com.packapuff.services.user.service.converter;

import com.packapuff.services.user.service.dto.UserResponse;
import com.packapuff.services.user.service.dto.CreateUserRequest;
import com.packapuff.services.user.service.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    private static final Logger log = LoggerFactory.getLogger(UserConverter.class);

    public User convertToUserEntity(CreateUserRequest request) {
        log.trace("Entering convertToUserEntity");

        User user = new User();

        user.setRoleId(1);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());

        user.setCreatedTimestamp(OffsetDateTime.now());
        user.setUpdatedTimestamp(OffsetDateTime.now());

        log.trace("Exiting convertToUserEntity");
        return user;
    }

    public UserResponse convertToUserResponse(User user) {
        log.debug("Entering convertToUserResponse");

        UserResponse response = new UserResponse();

        response.setUserId(user.getUserId());
        response.setRoleId(user.getRoleId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setCreatedTimestamp(user.getCreatedTimestamp());
        response.setUpdatedTimestamp(user.getUpdatedTimestamp());

        log.debug("Exiting convertToUserResponse");
        return response;
    }

    public List<UserResponse> convertToUserResponseList(List<User> userList) {
        log.trace("Entering convertToUserResponseList");

        List<UserResponse> responseList = new ArrayList<>();

        for (User user: userList) {
            responseList.add(convertToUserResponse(user));
        }

        log.trace("Exiting convertToUserResponseList");
        return responseList;
    }
}
