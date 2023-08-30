package com.packapuff.services.site.service.repository;



import com.packapuff.services.site.service.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

    Optional<List<Site>> findBySiteIdIn(List<Integer> siteIds);

}
