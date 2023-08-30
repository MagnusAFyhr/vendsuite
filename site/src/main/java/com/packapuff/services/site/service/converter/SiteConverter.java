package com.packapuff.services.site.service.converter;


import com.packapuff.services.site.service.dto.CreateSiteRequest;
import com.packapuff.services.site.service.dto.SiteResponse;
import com.packapuff.services.site.service.entity.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class SiteConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteConverter.class);

    public Site convertToSiteEntity(CreateSiteRequest request) {
        LOGGER.trace("Entering SiteConverter::convertToSiteEntity()");

        Site site = new Site();

        site.setSiteName(request.getSiteName());
        site.setSiteType(request.getSiteType());
        site.setSiteOwnerId(request.getSiteOwnerId());
        site.setStreetAddress(request.getStreetAddress());
        site.setZipcode(request.getZipcode());
        site.setCity(request.getCity());
        site.setStateCode(request.getStateCode());

        site.setCreatedTimestamp(OffsetDateTime.now());
        site.setUpdatedTimestamp(OffsetDateTime.now());

        LOGGER.trace("Exiting SiteConverter::convertToSiteEntity()");
        return site;
    }

    public SiteResponse convertToSiteResponse(Site site) {
        LOGGER.trace("Entering SiteConverter::convertToSiteResponse()");

        SiteResponse response = new SiteResponse();

        response.setSiteId(site.getSiteId());
        response.setSiteName(site.getSiteName());
        response.setSiteType(site.getSiteType());
        response.setSiteOwnerId(site.getSiteOwnerId());
        response.setStreetAddress(site.getStreetAddress());
        response.setZipcode(site.getZipcode());
        response.setCity(site.getCity());
        response.setStateCode(site.getStateCode());

        response.setCreatedTimestamp(site.getCreatedTimestamp());
        response.setUpdatedTimestamp(site.getUpdatedTimestamp());

        LOGGER.trace("Exiting SiteConverter::convertToSiteResponse()");
        return response;
    }

    public List<SiteResponse> convertToSiteResponseList(List<Site> siteList) {
        LOGGER.trace("Entering SiteConverter::convertToSiteResponseList()");

        List<SiteResponse> responseList = new ArrayList<>();

        for (Site site: siteList) {
            responseList.add(convertToSiteResponse(site));
        }

        LOGGER.trace("Exiting SiteConverter::convertToSiteResponseList()");
        return responseList;
    }
}
