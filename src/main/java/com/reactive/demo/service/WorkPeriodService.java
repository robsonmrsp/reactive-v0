package com.reactive.demo.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.demo.entity.WorkPeriodEntity;

import reactor.core.publisher.Mono;

@Service
public class WorkPeriodService {

	@Autowired
	WorkPeriodRepository repository;

	public Mono<LocalDate> findWorkPeriod(UUID clientId, UUID companyId) {
		return Mono.fromCallable(() -> LocalDate.now());
	}

	public Mono<WorkPeriodEntity> save(UUID clientId, UUID companyId, UUID contactId,
			LocalDate periodEffect) {
		return Mono.fromCallable(() -> repository.findByClientIdAndCompanyId(clientId, companyId))
				.flatMap(entity -> {
					if (entity.isPresent() && periodEffect.isBefore(entity.get().getPeriodEffect())) {
						return Mono.fromCallable(() -> repository.save(
								entity.map(workPeriodEntity -> update(workPeriodEntity, periodEffect, contactId))
										.get()));
					} else if (entity.isEmpty()) {
						return Mono.fromCallable(
								() -> repository.save(create(clientId, companyId, contactId, periodEffect)));
					}

					return Mono.empty();
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
