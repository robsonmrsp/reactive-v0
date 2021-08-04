package com.reactive.demo.controller;


import java.time.LocalDate;
import java.util.List;

import com.reactive.demo.entity.OperationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationRequest {

  private String code;
  private LocalDate periodEffect;
  private Boolean considerAsBillingGrossRevenue;
  private String description;
  private OperationType operationType;
  private List<OperationTaxRequest> taxes;

}
