package com.reactive.demo.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.reactive.demo.service.OperationService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/v1/operations")
public class OperationController {

	private final OperationService service;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public OperationController(OperationService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Mono<OperationResponse> create(@RequestParam("clientId") UUID clientId,
			@RequestBody OperationRequest creationRequest,
			ServerWebExchange exchange) {
		final var companyId = UUID.randomUUID();
		final var contactId = UUID.randomUUID();

		return this.service
				.save(OperationMapper.toEntity(creationRequest, clientId, companyId, contactId))
				.map(OperationMapper::toResponse)
				.doOnSuccess(response -> exchange.getResponse().setStatusCode(HttpStatus.CREATED))
				.onErrorMap(e -> new RuntimeException())
				.subscribeOn(Schedulers.boundedElastic());

	} 

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Mono<OperationResponse> update(ServerWebExchange exchange,
			@PathVariable("id") UUID id,
			@RequestParam("clientId") UUID clientId,
			@RequestBody OperationRequest operationRequest) {

		return this.service.findById(id, clientId, UUID.randomUUID())
				.map(entity -> OperationMapper
						.toEntity(operationRequest, clientId, UUID.randomUUID(), UUID.randomUUID(), entity))
				.flatMap(this.service::save)
				.map(OperationMapper::toResponse)
				.doOnSuccess(response -> exchange.getResponse().setStatusCode(HttpStatus.OK))
				.onErrorMap(e -> new RuntimeException())
				.subscribeOn(Schedulers.boundedElastic());
	}

	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Mono<OperationDetailedResponse> findById(@PathVariable("id") UUID id,
			@RequestParam("clientId") UUID clientId,
			ServerWebExchange exchange) {
		final var companyId = UUID.randomUUID();

		return this.service.findById(id, clientId, companyId)
				.map(OperationMapper::toDetailedResponse)
				.doOnSuccess(response -> exchange.getResponse().setStatusCode(HttpStatus.OK))
				.onErrorMap(e -> new RuntimeException(
						HttpStatus.INTERNAL_SERVER_ERROR.toString()))
				.subscribeOn(Schedulers.boundedElastic());
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public Mono<Page<OperationResponse>> findAll(@RequestParam("clientId") UUID clientId,
			PageFilter pageFilter,
			OperationFilterRequest filters,
			ServerWebExchange exchange) {
		final var companyId = UUID.randomUUID();

		return this.service
				.findAll(clientId, companyId, OperationMapper.toFilter(filters), pageFilter.toPageable())
				.map(p -> p.map(OperationMapper::toResponse))
				.doOnSuccess(response -> exchange.getResponse().setStatusCode(HttpStatus.OK))
				.onErrorMap(e -> new RuntimeException(e))
				.subscribeOn(Schedulers.boundedElastic());

	}
}
