package com.packapuff.services.machine.service;

import com.packapuff.services.machine.service.event.MachineEventProducer;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.services.machine.service.converter.MachineConverter;
import com.packapuff.services.machine.service.dto.*;
import com.packapuff.services.machine.service.entity.Machine;
import com.packapuff.services.machine.service.repository.MachineRepository;
import com.packapuff.services.machine.service.utility.MachineUtilities;
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
import java.util.UUID;

import static com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary.*;

@Service
public class MachineServiceImpl implements MachineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineServiceImpl.class);

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MachineConverter machineConverter;

    @Autowired
    private MachineUtilities machineUtilities;
    
    @Autowired
    private MachineEventProducer machineEventProducer;

    public MachineResponse createMachine(CreateMachineRequest createMachineRequest) {
        LOGGER.trace("Creating machine");

        MachineResponse machineResponse;
        try {
            // convert machine request to machine object
            Machine machine = machineConverter.convertToMachineEntity(createMachineRequest);

            // push to db and return db object
            Machine machineEntity = machineRepository.save(machine); // this will throw an exception

            // convert entity to response
            machineResponse = machineConverter.convertToMachineResponse(machineEntity);

            // emit event
            machineEventProducer.publishMachineCreatedEvent(machineResponse);

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

        LOGGER.trace("Created machine");
        return machineResponse;
    }

    public List<MachineResponse> getMachines(GetMachinesRequest getMachinesRequest) {
        LOGGER.trace("Retrieving machines (get)");

        List<MachineResponse> machineResponses;
        try {
            // get object from db
            List<Machine> machineEntities = machineRepository.findByMachineIdIn(getMachinesRequest.getMachineIds()).orElseThrow(NoSuchElementException::new);

            // convert entity to response
            machineResponses = machineConverter.convertToMachineResponseList(machineEntities);

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
        LOGGER.trace("Retrieved machines (get)");
        return machineResponses;
    }

    @Override
    public MachineResponse getMachineByTelemetryId(Integer telemetryId) {
        LOGGER.trace("Entering getMachineByTelemetryId");

        MachineResponse machineResponse;
        try {
            // get object from db
            Machine machineEntity = machineRepository.findByTelemetryId(telemetryId);

            // convert entity to response
            machineResponse = machineConverter.convertToMachineResponse(machineEntity);

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
        LOGGER.trace("Exiting getMachineByTelemetryId");
        return machineResponse;
    }

    public List<MachineResponse> listMachines(ListMachinesRequest listMachinesRequest) {
        LOGGER.trace("Retrieving machines (list)");

        List<MachineResponse> machineResponses;
        try {
            // get objects from db
            // TODO:filter
            List<Machine> machineEntities = machineRepository.findAll();

            // convert entity to response
            machineResponses = machineConverter.convertToMachineResponseList(machineEntities);

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
        LOGGER.trace("Retrieved machines (list)");
        return machineResponses;

    }

    public List<MachineResponse> updateMachines(List<UpdateMachineRequest> updateMachineRequestList) {
        LOGGER.trace("Updating users");

        List<MachineResponse> machineResponses;
        try {
            // collect user ids
            List<Integer> machineIds = new ArrayList<>();
            for (UpdateMachineRequest updateMachineRequest : updateMachineRequestList) {
                machineIds.add(updateMachineRequest.getMachineId());
            }

            // get user entities
            List<Machine> machines = machineRepository.findByMachineIdIn(machineIds).orElseThrow(NoSuchElementException::new);
            // update user entities
            for (int i = 0; i < machines.size() - 1; i++) {

                if (null != updateMachineRequestList.get(i).getLockId()) {
                    machines.get(i).setLockId(updateMachineRequestList.get(i).getLockId());
                    // TODO : make sure lock exists
                }

                if (null != updateMachineRequestList.get(i).getProductMapId()) {
                    machines.get(i).setProductMapId(updateMachineRequestList.get(i).getProductMapId());
                    // TODO: create vending columns based on product map
                }

                if (null != updateMachineRequestList.get(i).getTelemetryId()) {
                    machines.get(i).setTelemetryId(updateMachineRequestList.get(i).getTelemetryId());
                }

                if (null != updateMachineRequestList.get(i).getScreenId()) {
                    machines.get(i).setScreenId(updateMachineRequestList.get(i).getScreenId());
                    // TODO : make sure screen exists
                }

                if (null != updateMachineRequestList.get(i).getSiteId()) {
                    machines.get(i).setSiteId(updateMachineRequestList.get(i).getSiteId());
                    // TODO : make sure site exists
                }

                if (!machineUtilities.isBlank(updateMachineRequestList.get(i).getMachineName())) {
                    machines.get(i).setMachineName(updateMachineRequestList.get(i).getMachineName());
                }

                if (null != updateMachineRequestList.get(i).getIsConnected()) {
                    machines.get(i).setIsConnected(updateMachineRequestList.get(i).getIsConnected());
                }

                if (null != updateMachineRequestList.get(i).getOperationStartTime()) {
                    machines.get(i).setOperationStartTime(updateMachineRequestList.get(i).getOperationStartTime());
                }

                if (null != updateMachineRequestList.get(i).getOperationEndTime()) {
                    machines.get(i).setOperationEndTime(updateMachineRequestList.get(i).getOperationEndTime());
                }

                machines.get(i).setUpdatedTimestamp(OffsetDateTime.now());
            }

            // push to db and return db object
            List<Machine> machineEntities = machineRepository.saveAll(machines); // this will throw an exception

            // convert entity to response
            machineResponses = machineConverter.convertToMachineResponseList(machineEntities);

            // emit event(s)
            for (MachineResponse machineResponseForEvent: machineResponses) {
                machineEventProducer.publishMachineUpdatedEvent(machineResponseForEvent);
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
        LOGGER.trace("Updated users");
        return machineResponses;
    }

    public MachineResponse deleteMachine(Integer machineId) {
        LOGGER.trace("Deleting machine: {}", machineId);

        MachineResponse machineResponse;
        try {
            Machine machine = machineRepository.findById(machineId).orElseThrow(NoSuchElementException::new);
            machineRepository.delete(machine);
            LOGGER.trace("Deleted Machine " + machineId.toString());

            // convert entity to response
            machineResponse = machineConverter.convertToMachineResponse(machine);

            // emit event
            machineEventProducer.publishMachineCreatedEvent(machineResponse);

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
        LOGGER.trace("Deleted machine: {}", machineId);
        return machineResponse;
    }
}
