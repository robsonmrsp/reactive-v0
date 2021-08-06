package com.reactive.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reactive.demo.controller.OperationMapper;
import com.reactive.demo.entity.OperationEntity;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@AllArgsConstructor
public class OperationService {

	private final OperationRepository repository;
	private final OperationMapper operationMapper;
	private final WorkPeriodService workPeriodService;

	// XNOTE: como tratar o orElse que existia abaixo
	public Mono<Integer> getNextAvailableCode(UUID companyId, UUID clientId) {
		return repository.findLastUsedCode(companyId, clientId)
				.map(lastValue -> lastValue + 1);
	}

	public Mono<Boolean> checkIfCodeAlreadyExistsForCompany(Integer code, UUID companyId,
			UUID clientId) {
		return repository.existsByCodeAndCompanyIdAndClientId(code, companyId, clientId);
	}

	// XNOTE: várias operaçoes feitas nos mappers vão precisar ser reactives
	public Mono<OperationEntity> save(final OperationEntity entity) {
		return this.repository
				.save(entity)
				.flatMap(this.operationMapper::fillTransients);
	}

	public Mono<OperationEntity> findById(final UUID id, final UUID clientId, final UUID companyId) {
		return repository.findByClientIdAndCompanyIdAndId(clientId, companyId, id)
				.flatMap(this.operationMapper::fillTransients);
	}

	// XNOTE ainda não estamos trazendo os objetos aninhados. OperationEntity ainda
	// virá sem o Set<OperationPeriodEffectEntity>
	public Mono<Page<OperationEntity>> findAll(final UUID clientId, final UUID companyId, final OperationFilter filters, final Pageable pageable) {
		return repository
				.findPage(companyId, clientId, pageable.getOffset(), pageable.getPageSize())
				.collectList()
				.zipWith(repository.count(companyId, clientId))
				.map(countAndList -> getPage(pageable, countAndList));
	}

	// XNOTE: Separando apenas para ficar mais claro por enquanto. Talvez possa ir
	// para uma classe utilitários
	private <T> Page<T> getPage(final Pageable pageable, Tuple2<List<T>, Long> countAndList) {
		return new PageImpl<T>(countAndList.getT1(), pageable, countAndList.getT2());
	};
}
