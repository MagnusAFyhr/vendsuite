package com.packapuff.vendsuite.tenant.service.repository;



import com.packapuff.vendsuite.tenant.service.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

    Optional<List<Tenant>> findByTenantIdIn(List<Integer> tenantIds);

}
