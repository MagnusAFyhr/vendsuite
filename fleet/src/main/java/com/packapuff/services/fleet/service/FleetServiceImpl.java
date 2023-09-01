package com.packapuff.services.fleet.service;

import com.packapuff.services.fleet.service.event.FleetEventProducer;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetResponse;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.services.fleet.service.converter.FleetConverter;
import com.packapuff.services.fleet.service.dto.*;
import com.packapuff.services.fleet.service.entity.Fleet;
import com.packapuff.services.fleet.service.machine.FleetMachineService;
import com.packapuff.services.fleet.service.repository.FleetRepository;
import com.packapuff.services.fleet.service.user.FleetUserService;
import com.packapuff.services.fleet.service.utility.FleetUtilities;
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
public class FleetServiceImpl implements FleetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleetServiceImpl.class);

    @Autowired
    private FleetRepository fleetRepository;

    @Autowired
    private FleetConverter fleetConverter;

    @Autowired
    private FleetUtilities fleetUtilities;

    @Autowired
    private FleetUserService fleetUserService;

    @Autowired
    private FleetMachineService fleetMachineService;

    @Autowired
    private FleetEventProducer fleetEventProducer;

    public FleetResponse createFleet(CreateFleetRequest createFleetRequest) {
        LOGGER.trace("Creating fleet");

        FleetResponse fleetResponse;
        try {
            // convert user request to user object
            Fleet fleet = fleetConverter.convertToFleetEntity(createFleetRequest);

            // push to db and return db object
            Fleet fleetEntity = fleetRepository.save(fleet); // this will throw an exception

            // convert entity to response
            fleetResponse = fleetConverter.convertToFleetResponse(fleetEntity);

            // emit event
            fleetEventProducer.publishFleetCreatedEvent(fleetResponse);

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

        LOGGER.trace("Created fleet");
        return fleetResponse;
    }

    public List<FleetResponse> getFleets(GetFleetsRequest getFleetsRequest) {
        LOGGER.trace("Retrieving fleets (get)");

        List<FleetResponse> fleetResponses;
        try {
            // get object from db
            List<Fleet> fleetEntities = fleetRepository.findByFleetIdIn(getFleetsRequest.getFleetIds()).orElseThrow(NoSuchElementException::new);

            // convert entity to response
            fleetResponses = fleetConverter.convertToFleetResponseList(fleetEntities);

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
        LOGGER.trace("Retrieved fleets (get)");
        return fleetResponses;
    }

    public List<FleetResponse> updateFleets(List<UpdateFleetRequest> updateFleetRequestList) {
        LOGGER.trace("Updating fleets");

        List<FleetResponse> fleetResponses;
        try {
            // collect user ids
            List<Integer> fleetIds = new ArrayList<>();
            for (UpdateFleetRequest updateFleetRequest : updateFleetRequestList) {
                fleetIds.add(updateFleetRequest.getFleetId());
            }

            // get user entities
            List<Fleet> fleets = fleetRepository.findByFleetIdIn(fleetIds).orElseThrow(NoSuchElementException::new);
            // update user entities
            for (int i = 0; i < fleets.size() - 1; i++) {
                if (!fleetUtilities.isBlank(updateFleetRequestList.get(i).getFleetName())) {
                    fleets.get(i).setFleetName(updateFleetRequestList.get(i).getFleetName());
                }
                if (!fleetUtilities.isBlank(updateFleetRequestList.get(i).getDescription())) {
                    fleets.get(i).setDescription(updateFleetRequestList.get(i).getDescription());
                }

                fleets.get(i).setUpdatedTimestamp(OffsetDateTime.now());
            }

            // push to db and return db object
            List<Fleet> fleetEntities = fleetRepository.saveAll(fleets); // this will throw an exception

            // convert entity to response
            fleetResponses = fleetConverter.convertToFleetResponseList(fleetEntities);

            // emit event(s)
            for (FleetResponse fleetResponseForEvent: fleetResponses) {
                fleetEventProducer.publishFleetUpdatedEvent(fleetResponseForEvent);
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
        LOGGER.trace("Updated fleets");
        return fleetResponses;
    }

    public FleetResponse deleteFleet(Integer fleetId) {
        LOGGER.trace("Deleting fleet: {}", fleetId);

        FleetResponse fleetResponse;
        try {
            // remove fleet users
            fleetUserService.deleteAllFleetUsersInFleet(fleetId);

            // remove fleet machines
            fleetMachineService.deleteAllFleetMachinesInFleet(fleetId);

            // delete fleet
            Fleet fleet = fleetRepository.findById(fleetId).orElseThrow(NoSuchElementException::new);
            fleetRepository.delete(fleet);

            // convert entity to response
            fleetResponse = fleetConverter.convertToFleetResponse(fleet);

            // emit event
            fleetEventProducer.publishFleetDeletedEvent(fleetResponse);

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
        LOGGER.trace("Deleted fleet: {}", fleetId);
        return fleetResponse;
    }

}
