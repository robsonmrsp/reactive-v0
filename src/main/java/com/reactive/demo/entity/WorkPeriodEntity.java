package com.reactive.demo.entity;

import java.time.LocalDate;
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

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("work_period")
public class WorkPeriodEntity {
	
	@Transient
	private boolean isNew;

	public boolean isNew() {
		return this.id == null;
	}

	@Id
	@Column("work_period_id")
	private UUID id;

	@Column("client_id")
	private UUID clientId;

	@Column("company_id")
	private UUID companyId;

	@Column("period_effect")
	private LocalDate periodEffect;
}
