package com.reactive.demo;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.reactive.demo")
@EnableTransactionManagement
public class R2dbcConfig extends AbstractR2dbcConfiguration {

  @Bean("connectionR2dbc")
  @Override
  public ConnectionFactory connectionFactory() {

    final var connectionFactory = new PostgresqlConnectionFactory(
        PostgresqlConnectionConfiguration.builder()
            .host("onviopostgres.int.thomsonreuters.com")
            .port(5452)
            .database("bm_br_escrita")
            .username("bm_br_escrita_user")
            .password("password")
            .build());

    return new ConnectionPool(
        ConnectionPoolConfiguration.builder(connectionFactory)
            .maxIdleTime(Duration.ofMillis(330000))
            .initialSize(1)
            .maxSize(6)
            .acquireRetry(3)
            .maxLifeTime(Duration.ofMillis(900000))
            .validationQuery("SELECT 1")
            .build());
  }

  @Bean("transactionManagerR2dbc")
  ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
    return new R2dbcTransactionManager(connectionFactory);
  }

}
