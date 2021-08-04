package com.reactive.demo.controller;



import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reactive.demo.entity.OperationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationPeriodEffectResponse {

  private UUID id;

  private LocalDate periodEffect;

  private boolean considerAsBillingGrossRevenue;

  private String description;

  private boolean current;

  private OperationType operationType;

  private List<OperationTaxResponse> taxes;
}
