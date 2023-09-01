package com.packapuff.services.fleet.service.user;

import com.packapuff.services.fleet.service.entity.Fleet;
import com.packapuff.services.fleet.service.event.FleetUserEventProducer;
import com.packapuff.services.fleet.service.utility.FleetUtilities;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetUserResponse;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.services.fleet.service.converter.FleetUserConverter;
import com.packapuff.services.fleet.service.dto.user.CreateFleetUserRequest;
import com.packapuff.services.fleet.service.dto.user.GetFleetsUsersRequest;
import com.packapuff.services.fleet.service.entity.FleetUser;
import com.packapuff.services.fleet.service.repository.FleetUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary.*;

// TODO : Update fleet user role

@Service
public class FleetUserServiceImpl implements FleetUserService {

    private static final Logger log = LoggerFactory.getLogger(FleetUserServiceImpl.class);

    @Autowired
    private FleetUserRepository fleetUserRepository;

    @Autowired
    private FleetUserConverter fleetUserConverter;

    @Autowired
    private FleetUserEventProducer fleetUserEventProducer;

    @Autowired
    private FleetUtilities utils;


    public FleetUserResponse createFleetUser(CreateFleetUserRequest createFleetUserRequest) {
        log.trace("Creating fleet user");

        FleetUserResponse fleetUserResponse;
        try {
            // validate user exists
            if (!utils.userExists(createFleetUserRequest.getUserId())) {
                throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
            }

            // convert user request to user object
            FleetUser fleetUser = fleetUserConverter.convertToFleetUserEntity(createFleetUserRequest);

            // push to db and return db object
            FleetUser fleetUserEntity = fleetUserRepository.save(fleetUser); // this will throw an exception

            // convert entity to response
            fleetUserResponse = fleetUserConverter.convertToFleetUserResponse(fleetUserEntity);

            // emit event
            fleetUserEventProducer.publishFleetUserCreatedEvent(fleetUserResponse);

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

        log.trace("Created fleet user");
        return fleetUserResponse;
    }

    public List<FleetUserResponse> getFleetsUsers(GetFleetsUsersRequest getFleetsUsersRequest) {
        log.trace("Retrieving fleet users (get)");

        List<FleetUserResponse> fleetUserResponses;
        try {
            // get object from db
            List<FleetUser> fleetUserEntities = fleetUserRepository.findByFleetIdIn(getFleetsUsersRequest.getFleetIds()).orElseThrow(NoSuchElementException::new);

            // convert entities to list of userIds
            // List<Integer> fleetUserIds = fleetUserEntities.stream().map(FleetUser::getUserId).toList();

            fleetUserResponses = fleetUserConverter.convertToFleetUserResponseList(fleetUserEntities);

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
        log.trace("Retrieved fleetUsers (get)");
        return fleetUserResponses;
    }

    public List<FleetUserResponse> listFleetsUsers(List<Integer> fleetIds) {
        log.trace("Retrieving fleet users (list)");

        List<FleetUserResponse> fleetUserResponses;
        try {
            // get objects from db
            // TODO:filter
            List<FleetUser> fleetUserEntities = fleetUserRepository.findByFleetIdIn(fleetIds).orElseThrow(NoSuchElementException::new);

            // convert entity to response
            fleetUserResponses = fleetUserConverter.convertToFleetUserResponseList(fleetUserEntities);

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
        log.trace("Retrieved fleet users (list)");
        return fleetUserResponses;

    }

    public FleetUserResponse deleteFleetUser(Integer fleetId, Integer userId) {
        log.trace("Deleting fleetUser: fleet : {}, user : {}", fleetId, userId);

        FleetUserResponse fleetUserResponse;
        try {
            FleetUser fleetUser = fleetUserRepository.findByFleetIdAndUserId(fleetId, userId).orElseThrow(NoSuchElementException::new);
            fleetUserRepository.delete(fleetUser);

            fleetUserResponse = fleetUserConverter.convertToFleetUserResponse(fleetUser);

            // emit event
            fleetUserEventProducer.publishFleetUserDeletedEvent(fleetUserResponse);

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
        log.trace("Deleted fleetUser: fleet : {}, user : {}", fleetId, userId);
        return fleetUserResponse;
    }

    @Override
    public List<FleetUserResponse> deleteUserFromAllFleets(Integer userId) {
        log.trace("Deleting all fleetUsers of user : {}", userId);

        List<FleetUserResponse> fleetUserResponses;
        try {
            List<FleetUser> fleetUserEntities = fleetUserRepository.findAllByUserId(userId);
            fleetUserRepository.deleteAll(fleetUserEntities);

            // convert entity to response
            fleetUserResponses = fleetUserConverter.convertToFleetUserResponseList(fleetUserEntities);

            // emit event(s)
            for (FleetUserResponse fleetUserResponseForEvent : fleetUserResponses) {
                fleetUserEventProducer.publishFleetUserDeletedEvent(fleetUserResponseForEvent);
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
        log.trace("Deleted all fleetUsers of user : {}", userId);
        return fleetUserResponses;
    }

    public List<FleetUserResponse> deleteAllFleetUsersInFleet(Integer fleetId) {
        log.trace("Deleting all fleetUsers of fleet : {}", fleetId);

        List<FleetUserResponse> fleetUserResponses;
        try {
            List<FleetUser> fleetUserEntities = fleetUserRepository.findByFleetId(fleetId).orElseThrow(NoSuchElementException::new);
            fleetUserRepository.deleteAll(fleetUserEntities);

            // convert entity to response
            fleetUserResponses = fleetUserConverter.convertToFleetUserResponseList(fleetUserEntities);

            // emit event(s)
            for (FleetUserResponse fleetUserResponseForEvent: fleetUserResponses) {
                fleetUserEventProducer.publishFleetUserUpdatedEvent(fleetUserResponseForEvent);
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
        log.trace("Deleted all fleetUsers of fleet : {}", fleetId);
        return fleetUserResponses;
    }
}
