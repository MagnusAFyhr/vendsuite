package com.packapuff.services.fleet.service.machine;

import com.packapuff.services.fleet.service.event.FleetMachineEventProducer;
import com.packapuff.services.fleet.service.utility.FleetUtilities;
import com.packapuff.vendsuite.common.event_models.responses.fleet.FleetMachineResponse;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.services.fleet.service.converter.FleetMachineConverter;
import com.packapuff.services.fleet.service.dto.machine.CreateFleetMachineRequest;
import com.packapuff.services.fleet.service.dto.machine.GetFleetsMachinesRequest;
import com.packapuff.services.fleet.service.entity.FleetMachine;
import com.packapuff.services.fleet.service.repository.FleetMachineRepository;
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

@Service
public class FleetMachineServiceImpl implements FleetMachineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleetMachineServiceImpl.class);

    @Autowired
    private FleetMachineRepository fleetMachineRepository;

    @Autowired
    private FleetMachineConverter fleetMachineConverter;

    @Autowired
    private FleetMachineEventProducer fleetMachineEventProducer;

    @Autowired
    private FleetUtilities utils;

    public FleetMachineResponse createFleetMachine(CreateFleetMachineRequest createFleetMachineRequest) {
        LOGGER.trace("Creating fleet machine");

        FleetMachineResponse fleetMachineResponse;
        try {
            // validate machine exists
            if (!utils.machineExists(createFleetMachineRequest.getMachineId())) {
                throw new VendSuiteException(VEND_SUITE_GENERIC_ENTITY_NOT_FOUND.getVendSuiteError());
            }

            // convert fleet machine request to fleet machine object
            FleetMachine fleetMachine = fleetMachineConverter.convertToFleetMachineEntity(createFleetMachineRequest);

            // save fleet machine to db
            FleetMachine fleetMachineEntity = fleetMachineRepository.save(fleetMachine); // this will throw an exception

            // convert entity to response
            fleetMachineResponse = fleetMachineConverter.convertToFleetMachineResponse(fleetMachineEntity);

            // emit event(s)
            fleetMachineEventProducer.publishFleetMachineCreatedEvent(fleetMachineResponse);

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

        LOGGER.trace("Created fleet machine");
        return fleetMachineResponse;
    }

    public List<FleetMachineResponse> getFleetsMachines(GetFleetsMachinesRequest getFleetMachinesRequest) {
        LOGGER.trace("Retrieving fleet machines (get)");

        List<FleetMachineResponse> fleetMachineResponses;
        try {
            // get object from db
            List<FleetMachine> fleetMachineEntities = fleetMachineRepository.findByFleetIdIn(getFleetMachinesRequest.getFleetIds()).orElseThrow(NoSuchElementException::new);
            
            // convert entities to list of machine Ids
            // List<Integer> fleetMachineIds = fleetMachineEntities.stream().map(FleetMachine::getMachineId).toList();

            // convert entity to response
            fleetMachineResponses = fleetMachineConverter.convertToFleetMachineResponseList(fleetMachineEntities);

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

        // convert db object to fleet machine response object; then return
        LOGGER.trace("Retrieved fleet machines (get)");
        return fleetMachineResponses;
    }

    public FleetMachineResponse deleteFleetMachine(Integer machineId) {
        LOGGER.trace("Removing fleet machine {}", machineId);

        FleetMachineResponse fleetMachineResponse;
        try {
            // find fleet machine in db
            FleetMachine fleetMachine = fleetMachineRepository.findByMachineId(machineId)
                    .orElseThrow(NoSuchElementException::new);
            fleetMachineRepository.delete(fleetMachine);

            // convert entity to response
            fleetMachineResponse = fleetMachineConverter.convertToFleetMachineResponse(fleetMachine);

            // emit event
            fleetMachineEventProducer.publishFleetMachineDeletedEvent(fleetMachineResponse);

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

        // convert db object into fleet machine response object; and return
        LOGGER.trace("Removed fleet machine {}", machineId);
        return fleetMachineResponse;
    }

    public List<FleetMachineResponse> deleteAllFleetMachinesInFleet(Integer fleetId) {
        LOGGER.trace("Deleting all fleetMachines of fleet : {}", fleetId);

        List<FleetMachineResponse> fleetMachineResponses;
        try {
            // delete all fleet machines by fleet id
            List<FleetMachine> fleetMachineEntities = fleetMachineRepository.findByFleetId(fleetId).orElseThrow(NoSuchElementException::new);
            fleetMachineRepository.deleteAll(fleetMachineEntities);

            // convert entity to response
            fleetMachineResponses = fleetMachineConverter.convertToFleetMachineResponseList(fleetMachineEntities);

            // emit event(s)
            for (FleetMachineResponse fleetMachineResponseForEvent: fleetMachineResponses) {
                fleetMachineEventProducer.publishFleetMachineDeletedEvent(fleetMachineResponseForEvent);
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
        LOGGER.trace("Deleted all fleetMachines of fleet : {}", fleetId);
        return fleetMachineResponses;
    }

}
