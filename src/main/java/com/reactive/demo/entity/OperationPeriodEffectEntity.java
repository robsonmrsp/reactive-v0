package com.reactive.demo.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

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
@Table("operation_period_effect")
public class OperationPeriodEffectEntity {

	@Id
	@Column("operation_period_effect_id")
	private UUID id;

	@Column("operation_id")
	private UUID operationId;

	@Column("client_id")
	private UUID clientId;

	@Column("company_id")
	private UUID companyId;

	@Column("period_effect")
	private LocalDate periodEffect;

	@Column("consider_as_billing_gross_revenue")
	private boolean considerAsBillingGrossRevenue;

	@Column("description")
	private String description;

	@Column("operation_type")
	private OperationType operationType;

	@MappedCollection(idColumn = "operation_period_effect_id")
	@Singular
	private Set<OperationTaxEntity> taxes;

	@Transient
	private boolean isNew;

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
}
