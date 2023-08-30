package com.packapuff.services.user.api;

import com.packapuff.services.user.service.dto.*;
import com.packapuff.services.user.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * [Insert Comments]
 *
 */
@RestController
public class UserControllerImpl implements UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerImpl.class);

    @Autowired
    private UserService userService;

    /**
     *
     * Creates a user and stores in DB
     *
     * @param createUserRequest request pojo
     *
     */
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        LOGGER.trace("Entering createUser");

        UserResponse user = userService.createUser(createUserRequest);

        LOGGER.trace("Exiting createUser");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     *
     * Gets a user from DB
     *
     * @param getUsersRequest request pojo
     *
     */
    public ResponseEntity<List<UserResponse>> getUsers(@Valid @RequestBody GetUsersRequest getUsersRequest) {
        LOGGER.trace("Entering getUser");

        List<UserResponse> users = userService.getUsers(getUsersRequest);

        LOGGER.trace("Exiting getUser");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     *
     * Gets users from DB as list
     *
     * @param listUsersRequests request pojo
     *
     */
    public ResponseEntity<List<UserResponse>> listUsers(@Valid @RequestBody ListUsersRequest listUsersRequests) {
        LOGGER.trace("Entering listUsers");

        List<UserResponse> users = userService.listUsers(listUsersRequests);

        LOGGER.trace("Exiting listUsers");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     *
     * Update users in DB from list
     *
     * @param updateUserRequestList request pojo
     *
     */
    public ResponseEntity<List<UserResponse>> updateUsers(@Valid @RequestBody List<UpdateUserRequest> updateUserRequestList) {
        LOGGER.trace("Entering updateUsers");

        List<UserResponse> users = userService.updateUsers(updateUserRequestList);

        LOGGER.trace("Exiting updateUsers");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     *
     * Delete user from DB
     *
     * @param userId user to be deleted
     *
     */
    public ResponseEntity<UserResponse> deleteUser(Integer userId) {
        LOGGER.trace("Entering deleteUser");

        UserResponse user = userService.deleteUser(userId);

        LOGGER.trace("Exiting deleteUser");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     *
     * Delete user from DB
     *
     * @param updateRFIDRequestList list of request pojo
     *
     */
    public ResponseEntity<List<UserResponse>> setRFID(List<UpdateRFIDRequest> updateRFIDRequestList) {
        LOGGER.trace("Entering setRFID");

        List<UserResponse> users = userService.setRFID(updateRFIDRequestList);

        LOGGER.trace("Exiting setRFID");
        return new ResponseEntity<>(users, HttpStatus.OK);    }
}
