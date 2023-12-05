package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.utilizandocontext;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobUtilizandoContext {

    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Bean(name = "JobUtilizandoContext")
    public Job JobUtilizandoContext(
            Step stepUtilizandoContextTasklet,
            Step stepUtilizandoContextReaderProcessorWriter,
            Step stepUtilizandoContextFinalTasklet) {
        return jobBuilderFactory
                .get("JOB_UTILIZANDO_CONTEXT")
                .start(stepUtilizandoContextTasklet)
                .next(stepUtilizandoContextReaderProcessorWriter)
                .next(stepUtilizandoContextFinalTasklet)
                .build();
    }
}
