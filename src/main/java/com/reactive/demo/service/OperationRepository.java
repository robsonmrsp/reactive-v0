package com.reactive.demo.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
//import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reactive.demo.entity.OperationEntity;

@Repository
public interface OperationRepository extends PagingAndSortingRepository<OperationEntity, UUID> {

  @Query("SELECT COALESCE(MAX(CAST(code AS INT)), 0) FROM operation WHERE company_id = :companyId AND client_id = :clientId")
  Optional<Integer> findLastUsedCode(
      @Param("companyId") UUID companyId, @Param("clientId") UUID clientId);

  Boolean existsByCodeAndCompanyIdAndClientId(Integer code, UUID companyId, UUID clientId);

  Optional<OperationEntity> findByClientIdAndCompanyIdAndId(final UUID clientId, final UUID companyId, final UUID id);

  List<OperationEntity> findByClientIdAndCompanyId(final UUID clientId, final UUID companyId, final Pageable pageable);

  long countByClientIdAndCompanyId(final UUID clientId, final UUID companyId);


}
