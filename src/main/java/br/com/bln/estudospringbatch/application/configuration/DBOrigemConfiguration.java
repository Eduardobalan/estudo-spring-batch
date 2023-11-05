package br.com.bln.estudospringbatch.application.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "origemEntityManagerFactory",
        transactionManagerRef = "origemTransactionManager",
        basePackages = {"br.com.bln.estudospringbatch.adapter.gateway.repository.origem"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class DBOrigemConfiguration {

    @Bean(name = "origem-datasource")
    @ConfigurationProperties(prefix = "origem.datasource")
    public DataSource criarDatasourceOrigem() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "origemEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean batchEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("origem-datasource") DataSource datasource) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");

        return builder.dataSource(datasource)
                .packages("br.com.bln.estudospringbatch.domain.entity.origem")
                .persistenceUnit("persistenceUnitOrigem")
                .properties(properties)
                .build();
    }

    @Bean(name = "origemTransactionManager")
    public PlatformTransactionManager batchTransactionManager(@Qualifier("origemEntityManagerFactory") EntityManagerFactory emFactory) {
        return new JpaTransactionManager(emFactory);
    }
}