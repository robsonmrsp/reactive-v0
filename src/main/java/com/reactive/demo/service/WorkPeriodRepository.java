package com.reactive.demo.service;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reactive.demo.entity.WorkPeriodEntity;

@Repository
public interface WorkPeriodRepository extends CrudRepository<WorkPeriodEntity, UUID> {

  Optional<WorkPeriodEntity> findByClientIdAndCompanyId(UUID clientId, UUID companyId);
}
