package com.packapuff.services.site.service;

import com.packapuff.services.site.service.dto.CreateSiteRequest;
import com.packapuff.services.site.service.dto.GetSitesRequest;
import com.packapuff.services.site.service.dto.SiteResponse;
import com.packapuff.services.site.service.dto.UpdateSiteRequest;

import java.util.List;

public interface SiteService {

    SiteResponse createSite(CreateSiteRequest createSiteRequest);

    List<SiteResponse> getSites(GetSitesRequest getSitesRequest);

    List<SiteResponse> updateSites(List<UpdateSiteRequest> updateSiteRequestList);

    SiteResponse deleteSite(Integer siteId);
    
}
