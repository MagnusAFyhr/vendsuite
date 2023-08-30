package com.packapuff.services.point_of_sale.service;

import com.packapuff.services.point_of_sale.service.event.PointOfSaleEventProducer;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.services.point_of_sale.service.converter.PointOfSaleConverter;
import com.packapuff.services.point_of_sale.service.dto.CreatePointOfSaleRequest;
import com.packapuff.services.point_of_sale.service.dto.ListPointOfSalesRequest;
import com.packapuff.services.point_of_sale.service.dto.PointOfSaleResponse;
import com.packapuff.services.point_of_sale.service.entity.PointOfSale;
import com.packapuff.services.point_of_sale.service.repository.PointOfSaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary.*;

@Service
public class PointOfSaleServiceImpl implements PointOfSaleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointOfSaleServiceImpl.class);

    @Autowired
    private PointOfSaleRepository pointOfSaleRepository;

    @Autowired
    private PointOfSaleConverter pointOfSaleConverter;

    @Autowired
    private PointOfSaleEventProducer pointOfSaleEventProducer;

    public PointOfSaleResponse createPointOfSale(CreatePointOfSaleRequest createPointOfSaleRequest) {
        LOGGER.debug("Creating pointOfSale");

        PointOfSaleResponse pointOfSaleResponse;
        try {
            // convert pointOfSale request to pointOfSale object
            PointOfSale pointOfSale = pointOfSaleConverter.convertToPointOfSaleEntity(createPointOfSaleRequest);

            // push to db and return db object
            PointOfSale pointOfSaleEntity = pointOfSaleRepository.save(pointOfSale); // this will throw an exception

            // convert entity to response
            pointOfSaleResponse = pointOfSaleConverter.convertToPointOfSaleResponse(pointOfSaleEntity);

            // emit event
            pointOfSaleEventProducer.publishPointOfSaleCreatedEvent(pointOfSaleResponse);

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

        LOGGER.debug("Created pointOfSale");
        return pointOfSaleResponse;
    }

    public List<PointOfSaleResponse> getPointOfSales(List<Integer> pointOfSaleIds) {
        LOGGER.debug("Retrieving pointOfSale: {}", pointOfSaleIds.toString());

        List<PointOfSaleResponse> pointOfSaleResponses;
        try {
            // get object from db
            List<PointOfSale> pointOfSaleEntities = pointOfSaleRepository.findAllById(pointOfSaleIds);

            // convert entity to response
            pointOfSaleResponses = pointOfSaleConverter.convertToPointOfSaleResponseList(pointOfSaleEntities);

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

        // convert db object to pointOfSale response object; then return
        LOGGER.debug("Retrieved pointOfSale: {}", pointOfSaleIds.toString());
        return pointOfSaleResponses;
    }

    public List<PointOfSaleResponse> listPointOfSales(ListPointOfSalesRequest listPointOfSalesRequest) {
        LOGGER.debug("Retrieving pointOfSales");

        List<PointOfSaleResponse> pointOfSaleResponses;
        try {

            // get objects from db (filter)
            // List<PointOfSale> pointOfSaleEntities = pointOfSaleRepository.findPointOfSalesByTelemetryIdAndProductId(
            //        listPointOfSalesRequest.getTelemetryId(),
            //        listPointOfSalesRequest.getProductId()).orElse(new ArrayList<>());
            List<PointOfSale> pointOfSaleEntities = pointOfSaleRepository.findAll();

            // convert entity to response
            pointOfSaleResponses = pointOfSaleConverter.convertToPointOfSaleResponseList(pointOfSaleEntities);


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

        // convert db objects into pointOfSale response objects; and return
        LOGGER.debug("Retrieved pointOfSales");
        return pointOfSaleResponses;

    }
}
