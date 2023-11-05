package br.com.bln.estudospringbatch.domain.batchs;

import br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida.listener.CicloDeVidaJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobVisualizarCicloDeVidaSpringBatch {

    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Bean(name = "JobVisualizarCicloDeVidaSpringBatch")
    public Job JobVisualizarCicloDeVidaSpringBatch(
            Step stepCicloDeVidaTasklet,
            Step stepCicloDeVidaReaderProcessorWriter) {
        return jobBuilderFactory
                .get("JOB_VISUALIZAR_CICLO_DE_VIDA_SPRING_BATCH")
                .listener(new CicloDeVidaJobExecutionListener())
                .start(stepCicloDeVidaTasklet)
                .next(stepCicloDeVidaReaderProcessorWriter)
                .build();
    }
}
