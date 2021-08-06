package com.reactive.demo.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.demo.entity.WorkPeriodEntity;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class WorkPeriodService {

	private final WorkPeriodRepository repository;

	public Mono<LocalDate> findWorkPeriod(UUID clientId, UUID companyId) {
		return repository.findByClientIdAndCompanyId(clientId, companyId).map(WorkPeriodEntity::getPeriodEffect);
	}

	public Mono<WorkPeriodEntity> save(UUID clientId, UUID companyId, UUID contactId,
			LocalDate periodEffect) {
		return repository.findByClientIdAndCompanyId(clientId, companyId)
				.flatMap(workPeriod -> {
					if (periodEffect.isBefore(workPeriod.getPeriodEffect())) {
						return repository.save(update(workPeriod, periodEffect, contactId));
					}
					return repository.save(create(clientId, companyId, contactId, periodEffect));
				});
	}

	private WorkPeriodEntity create(UUID clientId, UUID companyId, UUID contactId, LocalDate period) {

		WorkPeriodEntity entity = new WorkPeriodEntity(false, contactId, contactId, contactId, period);
		return entity;
	}

	private WorkPeriodEntity update(WorkPeriodEntity oldEntity, LocalDate period, UUID contactId) {
		WorkPeriodEntity entity = new WorkPeriodEntity(false, contactId, contactId, contactId, period);

		return entity;
	}
}
