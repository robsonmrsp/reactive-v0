package com.reactive.demo;

import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJdbcRepositories(value = "com.reactive.demo",
		excludeFilters =
				{@ComponentScan.Filter(
						type = FilterType.ASSIGNABLE_TYPE, classes = {ReactiveCrudRepository.class})} )
@EnableTransactionManagement
public class InitialConfigConfiguration extends AbstractJdbcConfiguration {

	@Bean
	@Primary
	public NamedParameterJdbcOperations getNamedParameterJdbcOperations() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	@Bean
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://onviopostgres.int.thomsonreuters.com:5452/bm_br_escrita");
		dataSource.setUsername("bm_br_escrita_user");
		dataSource.setPassword("password");

		return dataSource;
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Override
	@Bean
	@Primary
	public JdbcMappingContext jdbcMappingContext(
			final Optional<NamingStrategy> namingStrategy,
			final JdbcCustomConversions customConversions) {
		return super.jdbcMappingContext(namingStrategy, customConversions);
	}
}