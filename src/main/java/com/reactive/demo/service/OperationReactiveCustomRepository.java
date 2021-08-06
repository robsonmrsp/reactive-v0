package com.reactive.demo.service;

import reactor.core.publisher.Mono;

public interface OperationReactiveCustomRepository<T, ID> {

  Mono<T> findById(ID id);

}
