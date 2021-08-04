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
@Table("operation_tax_simples_nacional")
public class OperationTaxSimplesNacionalEntity {

	@Id
	@Column("operation_tax_simples_nacional_id")
	private UUID id;

	@Column("operation_tax_id")
	private UUID operationTaxId;

	@Column("simples_nacional_attachment_id")
	private UUID simplesNacionalAttachmentId;

	@Transient
	private String attachmentName;

	@Transient
	private Integer attachmentCode;

	@Column("simples_nacional_activity_id")
	private UUID simplesNacionalActivityId;

	@Transient
	private String activityName;

	@Transient
	private String activityCode;

	@Column("company_id")
	private UUID companyId;

	@Transient
	private boolean isNew;

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

}
