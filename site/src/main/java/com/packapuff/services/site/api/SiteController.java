package com.packapuff.services.site.api;

import com.packapuff.services.site.service.SiteService;
import com.packapuff.services.site.service.dto.CreateSiteRequest;
import com.packapuff.services.site.service.dto.GetSitesRequest;
import com.packapuff.services.site.service.dto.SiteResponse;
import com.packapuff.services.site.service.dto.UpdateSiteRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class SiteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private SiteService siteService;

    /**
     *
     * Creates a site and stores in DB
     *
     * @param createSiteRequest request pojo
     *
     */
    public ResponseEntity<SiteResponse> createSite(
            @Valid @RequestBody CreateSiteRequest createSiteRequest
    ) {
        LOGGER.trace("Entering SiteController::createSite()");

        SiteResponse site = siteService.createSite(createSiteRequest);

        LOGGER.trace("Exiting SiteController::createSite()");
        return new ResponseEntity<>(site, HttpStatus.OK);
    }

    /**
     *
     * Gets site(s) from DB by id
     *
     * @param getSitesRequest request pojo
     *
     */
    public ResponseEntity<List<SiteResponse>> getSites(GetSitesRequest getSitesRequest) {
        LOGGER.trace("Entering SiteController::getSites()");

        List<SiteResponse> siteResponses = siteService.getSites(getSitesRequest);

        LOGGER.trace("Exiting SiteController::getSites()");
        return new ResponseEntity<>(siteResponses, HttpStatus.OK);
    }

    /**
     *
     * Updates site(s) in DB by id
     *
     * @param updateSiteRequests list of request pojo
     *
     */
    public ResponseEntity<List<SiteResponse>> updateSites(List<UpdateSiteRequest> updateSiteRequests) {
        LOGGER.trace("Entering SiteController::updateSites()");

        List<SiteResponse> siteResponses = siteService.updateSites(updateSiteRequests);

        LOGGER.trace("Exiting SiteController::updateSites()");
        return new ResponseEntity<>(siteResponses, HttpStatus.OK);
    }

    /**
     *
     * Deletes a site in DB by id
     *
     */
    public ResponseEntity<SiteResponse> deleteSite(Integer siteId) {
        LOGGER.trace("Entering SiteController::deleteSite()");

        SiteResponse siteResponse = siteService.deleteSite(siteId);

        LOGGER.trace("Exiting SiteController::deleteSite()");
        return new ResponseEntity<>(siteResponse, HttpStatus.OK);
    }
}
