package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.migrarpessoa;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class MigrarPessoaJob {

    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job jobMigrarPessoaImp(Step cleanStepImp,
                                      Step stepMigrarPessoaDestino,
                                      Step stepMigrarUsuarioDestino) {
        return jobBuilderFactory
                .get("JOB_MIGRAR_PESSOA_DESTINO")
                .start(cleanStepImp)
                .next(stepMigrarPessoaDestino)
                .next(stepMigrarUsuarioDestino)
                .build();
    }
}
