package com.packapuff.services.user.api;

import com.packapuff.services.user.service.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * [Insert Comments]
 *
 */

@Validated
@RequestMapping("/users")
public interface UserController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<UserResponse>> getUsers(@Valid @RequestBody GetUsersRequest requestBody);

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<UserResponse>> listUsers(@Valid @RequestBody ListUsersRequest requestBody);

    @PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<UserResponse>> updateUsers(@Valid @RequestBody List<UpdateUserRequest> requestBody);

    @DeleteMapping(value = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<UserResponse> deleteUser(@PathVariable("userId") Integer userId);

    @PatchMapping(value = "/rfid", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<UserResponse>> setRFID(@Valid @RequestBody List<UpdateRFIDRequest> requestBody);
}
