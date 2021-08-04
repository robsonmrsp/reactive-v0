package com.reactive.demo.controller;


import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class OperationDetailedResponse {

  private final UUID id;
  private final String code;

  private Set<OperationPeriodEffectResponse> periodEffects;
}
