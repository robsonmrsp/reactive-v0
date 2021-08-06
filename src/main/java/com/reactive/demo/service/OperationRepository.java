package com.reactive.demo.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reactive.demo.entity.OperationEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// 
@Repository
public interface OperationRepository extends R2dbcRepository<OperationEntity, UUID> {

	public static final String COUNT_PAGE = "select\n"
			+ "	count (*)"
			+ "from "
			+ "	operation "
			+ "WHERE "
			+ "	company_id = :companyId "
			+ " AND	client_id = :clientId ";

	public static final String FIND_PAGE_ALL = "SELECT "
			+ "	code , "
			+ "	client_id, "
			+ "	company_id, "
			+ "	create_date, "
			+ "	delete_date, "
			+ "	operation_id "
			+ "FROM "
			+ "	operation "
			+ "WHERE "
			+ "	company_id = :companyId "
			+ " AND	client_id = :clientId "
			+ "OFFSET :offset "
			+ "LIMIT :pageSize";

	public static final String FIND_LAST_USED_CODE = "SELECT "
			+ "	COALESCE(MAX(CAST(code AS INT)), "
			+ "	0) "
			+ "FROM "
			+ "	operation "
			+ "WHERE "
			+ "	company_id = :companyId "
			+ "	AND client_id = :clientId";

	@Query(FIND_LAST_USED_CODE)
	Mono<Integer> findLastUsedCode(@Param("companyId") UUID companyId, @Param("clientId") UUID clientId);

	Mono<Boolean> existsByCodeAndCompanyIdAndClientId(Integer code, UUID companyId, UUID clientId);

	Mono<OperationEntity> findByClientIdAndCompanyIdAndId(final UUID clientId, final UUID companyId, final UUID id);

	Flux<OperationEntity> findByClientIdAndCompanyId(final UUID clientId, final UUID companyId, final Pageable pageable);

	Mono<Long> countByClientIdAndCompanyId(final UUID clientId, final UUID companyId);

	// XNOTE : criados esses dois novos metodos para diferenciar dos demais que já
	// existiam. No original essa Page é criada por um customRepository
	@Query(FIND_PAGE_ALL)
	Flux<OperationEntity> findPage(UUID companyId, UUID clientId, OperationFilter filters, long offset, int pageSize);

	@Query(FIND_PAGE_ALL)
	Mono<Long> count(UUID companyId, UUID clientId, OperationFilter filters);
}
