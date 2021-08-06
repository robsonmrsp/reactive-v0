package com.reactive.demo.service;

import com.reactive.demo.entity.OperationEntity;
import com.reactive.demo.entity.OperationPeriodEffectEntity;
import com.reactive.demo.entity.OperationTaxEntity;
import io.r2dbc.spi.Row;
import java.util.UUID;
import java.util.function.BiFunction;

public class OperationRowMapper implements BiFunction<Row, Object, OperationEntity> {

  @Override
  public OperationEntity apply(Row row, Object o) {

    final var tax = OperationTaxEntity.builder()
        .id(row.get("operation_tax_id", UUID.class))
        .operationPeriodEffectId(row.get("operation_period_effect_id", UUID.class))
        .build();

    final var periodEffect = OperationPeriodEffectEntity.builder()
        .id(row.get("operation_period_effect_id", UUID.class))
        .operationId(row.get("operation_id", UUID.class))
        .taxe(tax)
        .build();

    return OperationEntity.builder()
        .id(row.get("operation_id", UUID.class))
        .periodEffect(periodEffect)
        .build();
  }
}
