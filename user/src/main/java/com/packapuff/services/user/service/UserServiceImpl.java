package com.packapuff.services.user.service;

import com.packapuff.services.user.service.converter.UserConverter;
import com.packapuff.services.user.service.dto.*;
import com.packapuff.services.user.service.entity.User;
import com.packapuff.services.user.service.event.UserEventProducer;
import com.packapuff.services.user.service.repository.UserRepository;
import com.packapuff.services.user.service.utility.UserUtilities;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserUtilities userUtilities;

    @Autowired
    private UserEventProducer userEventProducer;

    public UserResponse createUser(CreateUserRequest createUserRequest) {
        LOGGER.debug("Creating user");

        UserResponse userResponse;
        try {

            // convert user request to user object
            User user = userConverter.convertToUserEntity(createUserRequest);

            // push to db and return db object
            User userEntity = userRepository.save(user); // this will throw an exception

            // convert entity to response
            userResponse = userConverter.convertToUserResponse(userEntity);

            // emit event
            userEventProducer.publishUserCreatedEvent(userResponse);

        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        LOGGER.debug("Created user");
        return userResponse;
    }

    public List<UserResponse> getUsers(GetUsersRequest getUsersRequest) {
        LOGGER.debug("Retrieving users (get)");

        List<UserResponse> userResponses;
        try {
            // get object from db
            List<User> userEntities = userRepository.findByUserIdIn(getUsersRequest.getUserIds()).orElseThrow(NoSuchElementException::new);

            // convert entity to response
            userResponses = userConverter.convertToUserResponseList(userEntities);

        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        // convert db object to user response object; then return
        LOGGER.debug("Retrieved users (get)");
        return userResponses;
    }

    public List<UserResponse> listUsers(ListUsersRequest listUsersRequest) {
        LOGGER.debug("Retrieving users (list)");

        List<UserResponse> userResponses;
        try {
            // get objects from db
            // TODO: filter & pagination
            List<User> userEntities = userRepository.findAll();

            // convert entity to response
            userResponses = userConverter.convertToUserResponseList(userEntities);

        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        // convert db objects into purchase response objects; and return
        LOGGER.debug("Retrieved users (list)");
        return userResponses;

    }

    public List<UserResponse> updateUsers(List<UpdateUserRequest> updateUserRequestList) {
        LOGGER.debug("Updating users");

        List<UserResponse> userResponses;
        try {
            // collect user ids
            List<Integer> userIds = new ArrayList<>();
            for (UpdateUserRequest updateUserRequest : updateUserRequestList) {
                userIds.add(updateUserRequest.getUserId());
            }

            // get user entities
            List<User> users = userRepository.findByUserIdIn(userIds).orElseThrow(NoSuchElementException::new);
            // update user entities
            for (int i = 0; i < users.size() - 1; i++) {
                if (!userUtilities.isBlank(updateUserRequestList.get(i).getFirstName())) {
                    users.get(i).setFirstName(updateUserRequestList.get(i).getFirstName());
                }
                if (!userUtilities.isBlank(updateUserRequestList.get(i).getLastName())) {
                    users.get(i).setLastName(updateUserRequestList.get(i).getLastName());
                }
                if (!userUtilities.isBlank(updateUserRequestList.get(i).getEmail())) {
                    users.get(i).setEmail(updateUserRequestList.get(i).getEmail());
                }
                if (userUtilities.isValidPhoneNumber(updateUserRequestList.get(i).getPhoneNumber())) {
                    users.get(i).setPhoneNumber(updateUserRequestList.get(i).getPhoneNumber());
                }
                users.get(i).setUpdatedTimestamp(OffsetDateTime.now());
            }

            // push to db and return db object
            List<User> userEntities = userRepository.saveAll(users); // this will throw an exception

            // convert entity to response
            userResponses = userConverter.convertToUserResponseList(userEntities);

            // emit event(s)
            for (UserResponse userForEvent: userResponses) {
                userEventProducer.publishUserUpdatedEvent(userForEvent);
            }

        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        // convert db objects into user response objects; and return
        LOGGER.debug("Updated users");
        return userResponses;
    }

    public UserResponse deleteUser(Integer userId) {
        LOGGER.debug("Deleting user: {}", userId);

        UserResponse userResponse;
        try {
            User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
            userRepository.delete(user);

            // convert entity to response
            userResponse = userConverter.convertToUserResponse(user);

            // emit event
            userEventProducer.publishUserDeletedEvent(userResponse);

        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        // convert db object into user response object; and return
        LOGGER.debug("Deleted user: {}", userId);
        return userResponse;
    }

    @Override
    public List<UserResponse> deleteUsersOfTenant(Integer tenantId) {
        LOGGER.debug("Deleting users of tenant: {}", tenantId);

        List<UserResponse> userResponses;
        try {
            List<User> users = userRepository.findByTenantId(tenantId);
            userRepository.deleteAll(users);

            // convert entity to response
            userResponses = userConverter.convertToUserResponseList(users);

            // emit event
            for (UserResponse userResponse: userResponses) {
                userEventProducer.publishUserDeletedEvent(userResponse);
            }

        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        // convert db object into user response object; and return
        LOGGER.debug("Deleted users of tenant: {}", tenantId);
        return userResponses;
    }

    public List<UserResponse> setRFID(List<UpdateRFIDRequest> updateRFIDRequestList) {
        LOGGER.debug("Setting rfid(s)");

        List<UserResponse> userResponses;
        try {
            // collect user ids
            List<Integer> userIds = new ArrayList<>();
            for (UpdateRFIDRequest updateRFIDRequest : updateRFIDRequestList) {
                userIds.add(updateRFIDRequest.getUserId());
            }

            // get user entities
            List<User> users = userRepository.findByUserIdIn(userIds).orElseThrow(NoSuchElementException::new);

            // update user entities
            for (int i = 0; i < users.size() - 1; i++) {
                if (!users.get(i).getUserId().equals(updateRFIDRequestList.get(i).getUserId())) {
                    throw new VendSuiteException(VEND_SUITE_GENERIC_INVALID_INPUT.getVendSuiteError());
                }
                users.get(i).setRfid(updateRFIDRequestList.get(i).getRfid());
            }

            // push to db and return db object
            List<User> userEntities = userRepository.saveAll(users); // this will throw an exception

            // convert entity to response
            userResponses = userConverter.convertToUserResponseList(userEntities);

            // emit event(s)
            for (UserResponse userForEvent: userResponses) {
                userEventProducer.publishUserUpdatedEvent(userForEvent);
            }

        } catch (NoSuchElementException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
        } catch (DataIntegrityViolationException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_SAVE_FAILED.getVendSuiteError());
        } catch (DataAccessResourceFailureException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB_CONNECTION.getVendSuiteError());
        } catch (DataAccessException e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_DB.getVendSuiteError());
        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        // convert db objects into user response objects; and return
        LOGGER.debug("Set rfid(s)");
        return userResponses;
    }
}
