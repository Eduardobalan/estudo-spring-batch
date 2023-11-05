package br.com.bln.estudospringbatch.domain.batchs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class jobMigrarPessoa {

    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Bean(name = "jobMigrarPessoaDestino")
    public Job jobMigrarPessoaDestino(Step stepPessoaDestinoCleanTask,
                                      Step stepMigrarPessoaDestino,
                                      Step stepMigrarUsuarioDestino) {
        return jobBuilderFactory
                .get("JOB_MIGRAR_PESSOA_DESTINO")
                .start(stepPessoaDestinoCleanTask)
                .next(stepMigrarPessoaDestino)
                .next(stepMigrarUsuarioDestino)
                .build();
    }
}
