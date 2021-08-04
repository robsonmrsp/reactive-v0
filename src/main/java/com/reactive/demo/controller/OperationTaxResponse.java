package com.reactive.demo.controller;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationTaxResponse {

	private UUID id;
	private UUID parameterPeriodEffectTaxId;
	private Tax tax;

	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class Tax {

		private String code;
		private String name;
		private String initials;

		@JsonInclude(Include.NON_NULL)
		private SimplesNacional simplesNacional;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class SimplesNacional {

		private UUID id;
		private Attachment attachment;
		private Activity activity;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class Attachment {

		private UUID id;
		private Integer code;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@Getter
	public static class Activity {

		private UUID id;
		private String code;
		private String name;
	}
}
