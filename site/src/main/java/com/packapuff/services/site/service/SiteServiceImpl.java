package com.packapuff.services.site.service;

import com.packapuff.services.site.service.converter.SiteConverter;
import com.packapuff.services.site.service.dto.CreateSiteRequest;
import com.packapuff.services.site.service.dto.GetSitesRequest;
import com.packapuff.services.site.service.dto.SiteResponse;
import com.packapuff.services.site.service.dto.UpdateSiteRequest;
import com.packapuff.services.site.service.entity.Site;
import com.packapuff.services.site.service.event.SiteEventProducer;
import com.packapuff.services.site.service.repository.SiteRepository;
import com.packapuff.services.site.service.utilities.SiteUtilities;
import com.packapuff.vendsuite.common.exception_handler.exception.VendSuiteException;
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

public class SiteServiceImpl implements SiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private SiteConverter siteConverter;

    @Autowired
    private SiteUtilities utils;

    @Autowired
    private SiteEventProducer siteEventProducer;

    @Override
    public SiteResponse createSite(CreateSiteRequest createSiteRequest) {
        LOGGER.trace("Entering SiteServiceImpl::createSite()");

        SiteResponse siteResponse;
        try {
            // convert site request to site object
            Site site = siteConverter.convertToSiteEntity(createSiteRequest);
            
            // push to db and return db object
            Site siteEntity = siteRepository.save(site); // this will throw an exception

            // convert entity to response
            siteResponse = siteConverter.convertToSiteResponse(siteEntity);

            // emit event
            siteEventProducer.publishSiteCreatedEvent(siteResponse);

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
        
        LOGGER.trace("Exiting SiteServiceImpl::createSite()");
        return siteResponse;
    }

    @Override
    public List<SiteResponse> getSites(GetSitesRequest getSitesRequest) {
        LOGGER.trace("Entering SiteServiceImpl::getSites()");

        // save site entities
        List<SiteResponse> siteResponses;
        try {
            // get object from db
            List<Site> siteEntities = siteRepository.findBySiteIdIn(getSitesRequest.getSiteIds())
                    .orElseThrow(NoSuchElementException::new);

            // convert entity to response
            siteResponses = siteConverter.convertToSiteResponseList(siteEntities);

        }catch (NoSuchElementException e) {
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
        LOGGER.trace("Exiting SiteServiceImpl::getSites()");
        return siteResponses;
    }

    @Override
    public List<SiteResponse> updateSites(List<UpdateSiteRequest> updateSiteRequestList) {
        LOGGER.trace("Entering SiteServiceImpl::updateSites()");

        // save site entities
        List<SiteResponse> siteResponses;
        try {

            // collect site ids
            List<Integer> siteIds = new ArrayList<>();
            for (UpdateSiteRequest updateSiteRequest : updateSiteRequestList) {
                siteIds.add(updateSiteRequest.getSiteId());
            }

            // get site entities
            List<Site> sites = siteRepository.findBySiteIdIn(siteIds).orElseThrow();

            // update site entities
            for (int i = 0; i < sites.size() - 1; i++) {
                // site name
                if (!utils.isBlank(updateSiteRequestList.get(i).getSiteName())) {
                    sites.get(i).setSiteName(updateSiteRequestList.get(i).getSiteName());
                }
                // site type
                if (!utils.isBlank(updateSiteRequestList.get(i).getSiteType())) {
                    sites.get(i).setSiteType(updateSiteRequestList.get(i).getSiteType());
                }
                // site owner id
                if (null != updateSiteRequestList.get(i).getSiteOwnerId()) {
                    sites.get(i).setSiteOwnerId(updateSiteRequestList.get(i).getSiteOwnerId());
                }
                // street address
                if (!utils.isBlank(updateSiteRequestList.get(i).getStreetAddress())) {
                    sites.get(i).setStreetAddress(updateSiteRequestList.get(i).getStreetAddress());
                }
                // zipcode
                if (!utils.isBlank(updateSiteRequestList.get(i).getZipcode())) {
                    sites.get(i).setZipcode(updateSiteRequestList.get(i).getZipcode());
                }
                // city
                if (!utils.isBlank(updateSiteRequestList.get(i).getCity())) {
                    sites.get(i).setCity(updateSiteRequestList.get(i).getCity());
                }
                // state code
                if (!utils.isBlank(updateSiteRequestList.get(i).getStateCode())) {
                    sites.get(i).setStateCode(updateSiteRequestList.get(i).getStateCode());
                }

                sites.get(i).setUpdatedTimestamp(OffsetDateTime.now());
            }

            // push to db and return db object
            List<Site> siteEntities = siteRepository.saveAll(sites); // this will throw an exception

            // convert entity to response
            siteResponses = siteConverter.convertToSiteResponseList(siteEntities);

            // emit event(s)
            for (SiteResponse siteResponseForEvent: siteResponses) {
                siteEventProducer.publishSiteUpdatedEvent(siteResponseForEvent);
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
        
        LOGGER.trace("Exiting SiteServiceImpl::updateSites()");
        return siteResponses;
    }

    @Override
    public SiteResponse deleteSite(Integer siteId) {
        LOGGER.trace("Entering SiteServiceImpl::deleteSite()");

        SiteResponse siteResponse;
        try {
            // delete site
            Site site = siteRepository.findById(siteId)
                    .orElseThrow(EntityNotFoundException::new);
            siteRepository.delete(site);
            LOGGER.debug("Deleted Site " + siteId.toString());

            // convert entity to response
            siteResponse = siteConverter.convertToSiteResponse(site);

            // emit event
            siteEventProducer.publishSiteDeletedEvent(siteResponse);

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

        LOGGER.trace("Exiting SiteServiceImpl::deleteSite()");
        return siteResponse;
    }
}
