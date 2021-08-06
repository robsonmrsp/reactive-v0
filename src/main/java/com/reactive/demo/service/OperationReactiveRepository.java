package com.reactive.demo.service;

import com.reactive.demo.entity.OperationEntity;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationReactiveRepository
    extends ReactiveSortingRepository<OperationEntity, UUID> {

}
