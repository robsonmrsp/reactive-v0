package com.reactive.demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

//XNOTE: Como era de se esperar, depois de removidas as dependencias ao spring data-jdbc essas configs param de funcionar.
// Ao habilitar essa Configuration, vemos  o s eguinte erro:
// org.springframework.dao.InvalidDataAccessApiUsageException: Reactive Repositories are not supported by JDBC. Offending repository is com.reactive.demo.service.OperationRepository!
@Configuration
@EnableJdbcRepositories
public class InitialConfigConfiguration extends AbstractJdbcConfiguration {
	@Bean
	public NamedParameterJdbcOperations getNamedParameterJdbcOperations() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5433/bm_br_escrita");
		dataSource.setUsername("bm_br_escrita_user");
		dataSource.setPassword("password");

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}