package com.reactive.demo.controller;


import java.util.UUID;

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
public class OperationTaxRequest {

  private UUID id;
  private UUID parameterPeriodEffectTaxId;
  private UUID simplesNacionalAttachmentId;
  private UUID simplesNacionalActivityId;
}
