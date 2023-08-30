package com.packapuff.services.machine.service.column;

import com.packapuff.services.machine.service.event.VendingColumnEventProducer;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.services.machine.service.converter.VendingColumnConverter;
import com.packapuff.services.machine.service.dto.column.*;
import com.packapuff.services.machine.service.entity.VendingColumn;
import com.packapuff.services.machine.service.repository.VendingColumnRepository;

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
public class VendingColumnServiceImpl implements VendingColumnService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendingColumnServiceImpl.class);

    @Autowired
    private VendingColumnRepository vendingColumnRepository;

    @Autowired
    private VendingColumnConverter vendingColumnConverter;

    @Autowired
    private VendingColumnEventProducer vendingColumnEventProducer;

    public List<VendingColumnResponse> createVendingColumns(List<CreateVendingColumnRequest> createVendingColumnRequestList) {
        LOGGER.trace("Creating vending column(s)");

        List<VendingColumnResponse> vendingColumnResponseList = new ArrayList<>();
        try {
            for (CreateVendingColumnRequest createVendingColumnRequest: createVendingColumnRequestList) {
                // convert create vending column request to user object
                VendingColumn column = vendingColumnConverter.convertToVendingColumnEntity(createVendingColumnRequest);

                // push to db and return db object
                VendingColumn vendingColumnEntity = vendingColumnRepository.save(column); // this will throw an exception

                // convert entity to response
                VendingColumnResponse vendingColumnResponse = vendingColumnConverter.convertToVendingColumnResponse(vendingColumnEntity);
                vendingColumnResponseList.add(vendingColumnResponse);

                // emit event
                vendingColumnEventProducer.publishVendingColumnCreatedEvent(vendingColumnResponse);
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

        LOGGER.trace("Created vending column(s)");
        return vendingColumnResponseList;
    }

    public List<VendingColumnResponse> getVendingColumns(GetVendingColumnsRequest getVendingColumnsRequest) {
        LOGGER.trace("Retrieving vending columns (get)");

        List<VendingColumnResponse> vendingColumnResponses;
        try {
            // get object from db
            List<VendingColumn> columnEntities = vendingColumnRepository.findAllById(getVendingColumnsRequest.getVendingColumnsIds());

            // convert entity to response
            vendingColumnResponses = vendingColumnConverter.convertToVendingColumnResponseList(columnEntities);

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
        LOGGER.trace("Retrieved vending columns (get)");
        return vendingColumnResponses;
    }

    @Override
    public VendingColumnResponse getVendingColumnByMdbCode(Integer machineId, String mdbCode) {
        LOGGER.trace("Getting vending column for machine: {}, mdb: {}", machineId, mdbCode);

        VendingColumnResponse columnResponse;
        try {
            VendingColumn column = vendingColumnRepository.findByMachineIdAndMdbCode(machineId, mdbCode)
                    .orElseThrow(NoSuchElementException::new);

            // convert entity to response
            columnResponse = vendingColumnConverter.convertToVendingColumnResponse(column);

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
        LOGGER.trace("Got vending column for machine: {}, mdb: {}", machineId, mdbCode);
        return columnResponse;
    }

    public List<VendingColumnResponse> listVendingColumns(ListVendingColumnsRequest listVendingColumnsRequest) {
        LOGGER.trace("Retrieving machines (list)");

        List<VendingColumnResponse> vendingColumnResponses;
        try {
            // get objects from db
            // TODO:filter
            List<VendingColumn> columnEntities = vendingColumnRepository.findByMachineIdIn(listVendingColumnsRequest.getMachineIds()).orElseThrow(NoSuchElementException::new);

            // convert entity to response
            vendingColumnResponses = vendingColumnConverter.convertToVendingColumnResponseList(columnEntities);

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
        return vendingColumnResponses;

    }

    public List<VendingColumnResponse> updateVendingColumns(List<UpdateVendingColumnRequest> updateVendingColumnRequestList) {
        LOGGER.trace("Updating users");

        List<VendingColumnResponse> vendingColumnResponses;
        try {
            // update vending columns
            List<VendingColumn> vendingColumns = new ArrayList<>();
            for (UpdateVendingColumnRequest updateVendingColumnRequest : updateVendingColumnRequestList) {

                VendingColumn column = vendingColumnRepository.findById(updateVendingColumnRequest.getVendingColumnId())
                        .orElseThrow(NoSuchElementException::new);

                // perform updates
                Integer productId = updateVendingColumnRequest.getProductId();
                Integer liveQuantity = updateVendingColumnRequest.getLiveQuantity();
                // update product id if necessary
                if (null != productId) {
                    column.setProductId(productId);
                }
                // update live quantity
                if (null != liveQuantity) {
                    if (column.getMaxQuantity() < liveQuantity) {
                        throw new VendSuiteException(VEND_SUITE_GENERIC_INVALID_INPUT_RANGE.getVendSuiteError());
                    }

                    column.setLiveQuantity(liveQuantity);
                }
                column.setUpdatedTimestamp(OffsetDateTime.now());
                vendingColumns.add(column);
            }

            // push to db and return db object
            List<VendingColumn> vendingColumnEntities = vendingColumnRepository.saveAll(vendingColumns); // this will throw an exception

            // convert entity to response
            vendingColumnResponses = vendingColumnConverter.convertToVendingColumnResponseList(vendingColumnEntities);

            // emit event
            for (VendingColumnResponse response: vendingColumnResponses) {
                vendingColumnEventProducer.publishVendingColumnUpdatedEvent(response);
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
        LOGGER.trace("Updated vending columns");
        return vendingColumnResponses;
    }

    public List<VendingColumnResponse> deleteVendingColumns(Integer machineId) {
        LOGGER.trace("Deleting vending columns for machine: {}", machineId);

        List<VendingColumnResponse> columnResponseList;
        try {
            List<VendingColumn> columnList = vendingColumnRepository.findByMachineId(machineId)
                    .orElseThrow(NoSuchElementException::new);
            vendingColumnRepository.deleteAll(columnList);

            // convert entity to response
            columnResponseList = vendingColumnConverter.convertToVendingColumnResponseList(columnList);

            // emit event
            for (VendingColumnResponse columnResponse: columnResponseList) {
                vendingColumnEventProducer.publishVendingColumnDeletedEvent(columnResponse);
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
        LOGGER.trace("Deleted vending columns for machine: {}", machineId);
        return columnResponseList;
    }

    public VendingColumnResponse deleteVendingColumn(Integer machineId, String mdbCode) {
        LOGGER.trace("Deleting vending column for machine: {}, mdb: {}", machineId, mdbCode);

        VendingColumnResponse columnResponse;
        try {
            VendingColumn column = vendingColumnRepository.findByMachineIdAndMdbCode(machineId, mdbCode)
                    .orElseThrow(NoSuchElementException::new);
            vendingColumnRepository.delete(column);

            // convert entity to response
            columnResponse = vendingColumnConverter.convertToVendingColumnResponse(column);

            // emit event
            vendingColumnEventProducer.publishVendingColumnDeletedEvent(columnResponse);

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
        LOGGER.trace("Deleted vending column for machine: {}, mdb: {}", machineId, mdbCode);
        return columnResponse;
    }

}
