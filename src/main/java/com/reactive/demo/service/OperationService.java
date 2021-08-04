package com.reactive.demo.service;

import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reactive.demo.controller.OperationMapper;
import com.reactive.demo.entity.OperationEntity;
import com.reactive.demo.entity.OperationPeriodEffectEntity;

import reactor.core.publisher.Mono;

@Service
public class OperationService {

	private final OperationRepository repository;
	private final OperationMapper operationMapper;
	private final WorkPeriodService workPeriodService;

	public OperationService(OperationRepository repository,
			OperationMapper operationMapper,
			WorkPeriodService workPeriodService) {
		this.repository = repository;
		this.operationMapper = operationMapper;
		this.workPeriodService = workPeriodService;
	}

	public Integer getNextAvailableCode(UUID companyId, UUID clientId) {
		return repository.findLastUsedCode(companyId, clientId)
				.map(lastValue -> lastValue + 1).orElse(1);
	}

	public Boolean checkIfCodeAlreadyExistsForCompany(Integer code, UUID companyId,
			UUID clientId) {
		return repository.existsByCodeAndCompanyIdAndClientId(code, companyId, clientId);
	}

	public Mono<OperationEntity> save(final OperationEntity entity) {
		return Mono
				.fromCallable(() -> this.operationMapper.fillTransients(this.repository.save(entity)))
				.flatMap(operationEntity -> {
					Optional<OperationPeriodEffectEntity> periodEffectEntity = operationEntity
							.getPeriodEffects().stream()
							.min(Comparator.comparing(OperationPeriodEffectEntity::getPeriodEffect));
					if (periodEffectEntity.isEmpty()) {
						return Mono.empty();
					}
					return workPeriodService.save(
							entity.getClientId(), entity.getCompanyId(), UUID.randomUUID(),
							periodEffectEntity.get().getPeriodEffect()).thenReturn(operationEntity);
				});
	}

	public Mono<OperationEntity> findById(final UUID id, final UUID clientId, final UUID companyId) {
		return Mono.fromCallable(
				() -> repository.findByClientIdAndCompanyIdAndId(clientId, companyId, id)
						.map(this.operationMapper::fillTransients)
						.orElseThrow(RuntimeException::new));
	}

	public Mono<Page<OperationEntity>> findAll(final UUID clientId,
			final UUID companyId,
			final OperationFilter filters,
			final Pageable pageable) {

		return Mono.fromCallable(() -> {
			// separado somente apra tr onde adicionar um breakpoint
			Page<OperationEntity> findAll = repository.findAll(PageRequest.of(1, 10));
			return findAll;
		});
	}

}
