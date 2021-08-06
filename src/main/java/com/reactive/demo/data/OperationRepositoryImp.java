//package com.reactive.demo.data;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.r2dbc.convert.R2dbcConverter;
//import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
//import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
//import org.springframework.data.relational.repository.query.RelationalEntityInformation;
//import org.springframework.stereotype.Component;
//
//import com.reactive.demo.entity.OperationEntity;
//import com.reactive.demo.service.OperationFilter;
//import com.reactive.demo.service.OperationRepository;
//
//import reactor.core.publisher.Mono;
//
//@Component
//public class OperationRepositoryImp extends SimpleR2dbcRepository<OperationEntity, UUID> implements OperationRepository {
//
//	public OperationRepositoryImp(RelationalEntityInformation<OperationEntity, UUID> entity, R2dbcEntityOperations entityOperations,
//			R2dbcConverter converter) {
//		super(entity, entityOperations, converter);
//	}
//
//	@Override
//	public Optional<Integer> findLastUsedCode(UUID companyId, UUID clientId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean existsByCodeAndCompanyIdAndClientId(Integer code, UUID companyId, UUID clientId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<OperationEntity> findByClientIdAndCompanyIdAndId(UUID clientId, UUID companyId, UUID id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<OperationEntity> findByClientIdAndCompanyId(UUID clientId, UUID companyId, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long countByClientIdAndCompanyId(UUID clientId, UUID companyId) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public Mono<Page<OperationEntity>> findAll(UUID companyId, OperationFilter filters, Pageable pageable) {
//		
//
//	return null;
//}}
