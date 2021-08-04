package com.reactive.demo.controller;

import java.util.List;
import java.util.UUID;

import com.reactive.demo.entity.OperationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationFilterRequest {

	private List<String> expectedOrderEnum;
	private String code;
	private OperationType operationType;
	private String description;
	private UUID taxId;
	private UUID annexId;
	private UUID activityId;

}
