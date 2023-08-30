package com.packapuff.vendsuite.tenant.service;

import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
import com.packapuff.vendsuite.tenant.service.converter.TenantConverter;
import com.packapuff.vendsuite.tenant.service.dto.CreateTenantRequest;
import com.packapuff.vendsuite.tenant.service.dto.GetTenantsRequest;
import com.packapuff.vendsuite.tenant.service.dto.TenantResponse;
import com.packapuff.vendsuite.tenant.service.dto.UpdateTenantRequest;
import com.packapuff.vendsuite.tenant.service.entity.Tenant;
import com.packapuff.vendsuite.tenant.service.event.TenantEventProducer;
import com.packapuff.vendsuite.tenant.service.repository.TenantRepository;
import com.packapuff.vendsuite.tenant.service.utilities.TenantUtilities;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary.*;

public class TenantServiceImpl implements TenantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantServiceImpl.class);

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private TenantConverter tenantConverter;

    @Autowired
    private TenantUtilities utils;

    @Autowired
    private TenantEventProducer tenantEventProducer;

    @Override
    public TenantResponse createTenant(CreateTenantRequest createTenantRequest) {
        LOGGER.trace("Entering TenantServiceImpl::createTenant()");

        TenantResponse tenantResponse;
        try {
            // convert tenant request to tenant object
            Tenant tenant = tenantConverter.convertToTenantEntity(createTenantRequest);
            
            // push to db and return db object
            Tenant tenantEntity = tenantRepository.save(tenant); // this will throw an exception

            // convert entity to response
            tenantResponse = tenantConverter.convertToTenantResponse(tenantEntity);

            // emit event
            tenantEventProducer.publishTenantCreatedEvent(tenantResponse);

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
        
        LOGGER.trace("Exiting TenantServiceImpl::createTenant()");
        return tenantResponse;
    }

    @Override
    public List<TenantResponse> getTenants(GetTenantsRequest getTenantsRequest) {
        LOGGER.trace("Entering TenantServiceImpl::getTenants()");

        // save tenant entities
        List<TenantResponse> tenantResponses;
        try {
            // get object from db
            List<Tenant> tenantEntities = tenantRepository.findByTenantIdIn(getTenantsRequest.getTenantIds())
                    .orElseThrow(NoSuchElementException::new);

            // convert entity to response
            tenantResponses = tenantConverter.convertToTenantResponseList(tenantEntities);

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

        // convert db object to response object; then return
        LOGGER.trace("Exiting TenantServiceImpl::getTenants()");
        return tenantResponses;
    }

    @Override
    public List<TenantResponse> updateTenants(List<UpdateTenantRequest> updateTenantRequestList) {
        LOGGER.trace("Entering TenantServiceImpl::updateTenants()");

        // save tenant entities
        List<TenantResponse> tenantResponses;
        try {
            // collect tenant ids
            List<Integer> tenantIds = new ArrayList<>();
            for (UpdateTenantRequest updateTenantRequest : updateTenantRequestList) {
                tenantIds.add(updateTenantRequest.getTenantId());
            }

            // get tenant entities
            List<Tenant> tenants = tenantRepository.findByTenantIdIn(tenantIds).orElseThrow();

            // update tenant entities
            for (int i = 0; i < tenants.size() - 1; i++) {
                // tenant name
                if (!utils.isBlank(updateTenantRequestList.get(i).getTenantName())) {
                    tenants.get(i).setTenantName(updateTenantRequestList.get(i).getTenantName());
                }

                tenants.get(i).setUpdatedTimestamp(OffsetDateTime.now());
            }

            // push to db and return db object
            List<Tenant> tenantEntities = tenantRepository.saveAll(tenants); // this will throw an exception

            // convert entity to response
            tenantResponses = tenantConverter.convertToTenantResponseList(tenantEntities);

            // emit event(s)
            for (TenantResponse tenantResponseForEvent: tenantResponses) {
                tenantEventProducer.publishTenantUpdatedEvent(tenantResponseForEvent);
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
        
        LOGGER.trace("Exiting TenantServiceImpl::updateTenants()");
        return tenantResponses;
    }

    @Override
    public TenantResponse deleteTenant(Integer tenantId) {
        LOGGER.trace("Entering TenantServiceImpl::deleteTenant()");

        TenantResponse tenantResponse;
        try {
            // delete tenant
            Tenant tenantEntity = tenantRepository.findById(tenantId)
                    .orElseThrow(NoSuchElementException::new);
            tenantRepository.delete(tenantEntity);
            LOGGER.debug("Deleted Tenant " + tenantId.toString());

            // convert entity to response
            tenantResponse = tenantConverter.convertToTenantResponse(tenantEntity);

            // emit event
            tenantEventProducer.publishTenantCreatedEvent(tenantResponse);

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

        LOGGER.trace("Exiting TenantServiceImpl::deleteTenant()");
        return tenantResponse;
    }
}
