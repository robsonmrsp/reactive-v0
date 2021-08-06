package com.reactive.demo.service;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.reactive.demo.entity.WorkPeriodEntity;

import reactor.core.publisher.Mono;

@Repository
public interface WorkPeriodRepository extends R2dbcRepository<WorkPeriodEntity, UUID> {

	Mono<WorkPeriodEntity> findByClientIdAndCompanyId(UUID clientId, UUID companyId);
}
