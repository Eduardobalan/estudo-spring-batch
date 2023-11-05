package br.com.bln.estudospringbatch.application.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "destinoEntityManagerFactory",
        transactionManagerRef = "destinoTransactionManager",
        basePackages = {"br.com.bln.estudospringbatch.adapter.gateway.repository.destino"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class DBDestinoConfiguration {

    @Primary
    @Bean(name = "destino-datasource")
    @ConfigurationProperties(prefix = "destino.datasource")
    public DataSource criarDatasourceDestino() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "destinoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean batchEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("destino-datasource") DataSource datasource) {

        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");

        return builder
                .dataSource(datasource)
                .packages("br.com.bln.estudospringbatch.domain.entity.destino")
                .persistenceUnit("persistenceUnitDestino")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "destinoTransactionManager")
    public PlatformTransactionManager batchTransactionManager(@Qualifier("destinoEntityManagerFactory") EntityManagerFactory emFactory) {
        return new JpaTransactionManager(emFactory);
    }
}
