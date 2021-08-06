package com.reactive.demo.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageFilter {

	public Pageable toPageable() {

		return PageRequest.of(0, 5);
	}
}
