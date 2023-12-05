package br.com.bln.estudospringbatch.application.configuration;

import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringBatchConfig {

    @Bean(name = "batch-datasource")
    @ConfigurationProperties(prefix = "batch.datasource")
    public DataSource criarDatasourceBatch() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public BatchConfigurer configurer(@Qualifier("batch-datasource") DataSource datasource) {
        return new DefaultBatchConfigurer(datasource);
    }

    @Bean(name = "batch-repositorio")
    public JobRepository getJobRepository(@Qualifier("batch-datasource") DataSource datasource,
                                          BatchConfigurer batchConfigurer) throws Exception {
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(datasource);
        factoryBean.setTransactionManager(batchConfigurer.getTransactionManager());
        factoryBean.setTablePrefix("BATCH_");
        factoryBean.setIsolationLevelForCreate("PROPAGATION_REQUIRED");
        try {
            factoryBean.afterPropertiesSet();
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BatchConfigurationException(e);
        }
    }
}
