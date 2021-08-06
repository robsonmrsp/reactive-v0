package com.reactive.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class ReactiveV0Application {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveV0Application.class, args);
	}

}
