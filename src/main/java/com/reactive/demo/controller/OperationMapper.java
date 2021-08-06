package com.reactive.demo.controller;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.reactive.demo.entity.OperationEntity;
import com.reactive.demo.entity.OperationPeriodEffectEntity;
import com.reactive.demo.service.OperationFilter;

@Component
public class OperationMapper {

	public OperationEntity fillTransients(OperationEntity save) {
		// TODO Auto-generated method stub
		return save;
	}

	public static OperationResponse toResponse(final OperationEntity entity) {
		
		return OperationResponse.builder().build();
	}

	private static Optional<OperationPeriodEffectEntity> getCurrentPeriodEffect() {
		return Optional.empty();
	}

	private static boolean isCurrent(OperationPeriodEffectEntity periodEffectEntity) {
		final var today = LocalDate.now(Clock.systemUTC());
		return periodEffectEntity.getPeriodEffect().isBefore(today)
				|| periodEffectEntity.getPeriodEffect().isEqual(today);
	}

//	private List<OperationTaxResponse> toTaxesResponse(final Set<OperationTaxEntity> taxes) {
//		
//	}

	public static OperationEntity toEntity(
			final OperationRequest request,
			final UUID clientId,
			final UUID companyId,
			final UUID contactId,
			final OperationEntity entity) {

		final var isNew = Objects.isNull(entity);

		return OperationEntity.builder()
				.id(isNew ? UUID.randomUUID() : entity.getId())
				.isNew(isNew)
				.clientId(clientId)
				.companyId(companyId)
				.code(request.getCode())
//				.periodEffects(toPeriodEffects(request, clientId, companyId, contactId, entity))
				.build();

	}

	public static OperationEntity toEntity(
			final OperationRequest request,
			final UUID clientId,
			final UUID companyId,
			final UUID contactId) {
		return toEntity(request, clientId, companyId, contactId, null);
	}

//	private static Set<OperationPeriodEffectEntity> toPeriodEffects(
//			final OperationRequest request,
//			final UUID clientId,
//			final UUID companyId,
//			final UUID contactId,
//			OperationEntity entity) {
//		final var currentTime = LocalDateTime.now(Clock.systemUTC());
//		final var isNew = Objects.isNull(entity);
//
//		if (Objects.isNull(entity)) {
//			return Collections.emptySet();
//		}
//
//		return entity.getPeriodEffects().stream()
//				.map(periodEffectEntity -> {
//					var periodEffect = OperationPeriodEffectEntity.builder()
//							.id(isNew ? UuidHelper.randomUuid() : periodEffectEntity.getId())
//							.isNew(isNew)
//							.clientId(clientId)
//							.companyId(companyId)
//							.periodEffect(request.getPeriodEffect().withDayOfMonth(1))
//							.considerAsBillingGrossRevenue(request.getConsiderAsBillingGrossRevenue())
//							.description(request.getDescription())
//							.operationType(request.getOperationType())
//							.taxes(
//									isNew
//											? toOperationTaxes(request.getTaxes(), companyId, contactId)
//											: toOperationTaxes(
//													periodEffectEntity.getTaxes(),
//													request.getTaxes(),
//													companyId,
//													contactId))
//							.build();
//					periodEffect.setCreated(isNew ? currentTime : periodEffectEntity.getCreated());
//					periodEffect.setCreatedBy(isNew ? contactId : periodEffectEntity.getCreatedBy());
//					periodEffect.setChanged(currentTime);
//					periodEffect.setChangedBy(contactId);
//					return periodEffect;
//				})
//				.collect(Collectors.toSet());
//	}

//	private Set<OperationTaxEntity> toOperationTaxes(
//			final Set<OperationTaxEntity> operationTaxes,
//			final List<OperationTaxRequest> taxes,
//			final UUID companyId,
//			final UUID contactId) {
//		final var result = new HashSet<OperationTaxEntity>();
//
//		taxes.forEach(
//				tax -> {
//					final var operationTax = operationTaxes.stream()
//							.filter(o -> Objects.nonNull(tax.getId()) && tax.getId().equals(o.getId()))
//							.findFirst()
//							.orElse(new OperationTaxEntity());
//
//					final var isNew = Objects.isNull(operationTax.getId());
//					final var currentTime = LocalDateTime.now(Clock.systemUTC());
//
//					if (isNew) {
//						operationTax.setId(UuidHelper.randomUuid());
//						operationTax.setCreated(currentTime);
//						operationTax.setCreatedBy(contactId);
//					}
//
//					operationTax.setNew(isNew);
//					operationTax.setChanged(currentTime);
//					operationTax.setChangedBy(contactId);
//					operationTax.setCompanyId(companyId);
//					operationTax.setParameterPeriodEffectTaxId(tax.getParameterPeriodEffectTaxId());
//
//					if (Objects.nonNull(tax.getSimplesNacionalActivityId())
//							&& Objects.nonNull(tax.getSimplesNacionalAttachmentId())) {
//
//						var operationSn = operationTax.getOperationTaxSimplesNacional();
//
//						if (Objects.isNull(operationSn)) {
//							operationSn = new OperationTaxSimplesNacionalEntity();
//							operationSn.setNew(true);
//							operationSn.setCreated(currentTime);
//							operationSn.setCreatedBy(contactId);
//						}
//
//						operationSn.setCompanyId(companyId);
//						operationSn.setSimplesNacionalActivityId(tax.getSimplesNacionalActivityId());
//						operationSn.setSimplesNacionalAttachmentId(tax.getSimplesNacionalAttachmentId());
//						operationSn.setChanged(currentTime);
//						operationSn.setChangedBy(contactId);
//
//						operationTax.setOperationTaxSimplesNacional(operationSn);
//					}
//
//					result.add(operationTax);
//				});
//
//		return result;
//	}

//	private Set<OperationTaxEntity> toOperationTaxes(
//			final List<OperationTaxRequest> taxes, final UUID companyId, final UUID contactId) {
//		return toOperationTaxes(new HashSet<>(), taxes, companyId, contactId);
//	}

	public static OperationFilter toFilter(OperationFilterRequest filterRequest) {
		final var filter = new OperationFilter();
		filter.setExpectedOrderEnum(filterRequest.getExpectedOrderEnum());
		filter.setCode(filterRequest.getCode());
		filter.setOperationType(filterRequest.getOperationType());
		filter.setDescription(filterRequest.getDescription());
		filter.setTaxId(filterRequest.getTaxId());
		filter.setActivityId(filterRequest.getActivityId());
		filter.setAnnexId(filterRequest.getAnnexId());

		return filter;
	}

	public static OperationDetailedResponse toDetailedResponse(OperationEntity entity) {
		return OperationDetailedResponse.builder()
				.id(entity.getId())
				.code(entity.getCode())
//				.periodEffects(toListPeriodEffectResponse(entity.getPeriodEffects()))
				.build();
	}

//	private static Set<OperationPeriodEffectResponse> toListPeriodEffectResponse(
//			Set<OperationPeriodEffectEntity> periodEffects) {
//		var currentPeriodEffect = getCurrentPeriodEffect(periodEffects);
//
//		if (currentPeriodEffect.isEmpty()) {
//			return Collections.emptySet();
//		}
//
//		return periodEffects.stream()
//				.map(periodEffect -> OperationPeriodEffectResponse.builder()
//						.id(periodEffect.getId())
//						.periodEffect(periodEffect.getPeriodEffect())
//						.operationType(periodEffect.getOperationType())
//						.current(ObjectUtils
//								.nullSafeEquals(currentPeriodEffect.get().getId(), periodEffect.getId()))
//						.taxes(toTaxesResponse(periodEffect.getTaxes()))
//						.description(periodEffect.getDescription())
//						.considerAsBillingGrossRevenue(periodEffect.isConsiderAsBillingGrossRevenue())
//						.build())
//				.collect(Collectors.toSet());
//	}
}
