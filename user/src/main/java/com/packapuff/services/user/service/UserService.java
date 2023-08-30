package com.packapuff.services.user.service;

import com.packapuff.services.user.service.dto.*;

import java.util.List;

public interface UserService {


    UserResponse createUser(CreateUserRequest createUserRequest);

    List<UserResponse> getUsers(GetUsersRequest getUsersRequest);

    List<UserResponse> listUsers(ListUsersRequest listUsersRequest);

    List<UserResponse> updateUsers(List<UpdateUserRequest> updateUsersRequest);

    UserResponse deleteUser(Integer userId);

    List<UserResponse> deleteUsersOfTenant(Integer tenantId);

    List<UserResponse> setRFID(List<UpdateRFIDRequest> updateRFIDRequestList);

}
