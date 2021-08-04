package com.reactive.demo.entity;


import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
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
@Table("operation_tax_simples_nacional_tax")
public class OperationTaxSimplesNacionalTaxEntity {

	@Id
	@Column("operation_tax_simples_nacional_tax_id")
	private UUID id;

	@Column("operation_tax_simples_nacional_id")
	private UUID operationTaxSimplesNacionalId;

	@Column("simples_nacional_tax_id")
	private UUID simplesNacionalTaxId;

	@Column("status")
	private String status;

	@Transient
	private boolean isNew;

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

}
