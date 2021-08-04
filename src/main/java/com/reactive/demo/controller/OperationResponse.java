package com.reactive.demo.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.reactive.demo.entity.OperationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class OperationResponse {

  private final UUID id;
  private final String code;
  private final LocalDate periodEffect;
  private final Boolean considerAsBillingGrossRevenue;
  private final String description;
  private final OperationType operationType;
  private final List<OperationTaxResponse> taxes;

}
