package br.com.bln.estudospringbatch.domain.batchs.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class MigrarPessoaUsuarioCriandoVinculoJob {

    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job MigrarPessoaUsuarioCriandoVinculoJobImp(
            Step migrarPessoaDestinoStepImp,
            Step migrarUsuarioDestinoStepImp) {
        return jobBuilderFactory
                .get("JOB_MIGRAR_PESSOA_E_USUARIO_CRIANDO_VINCULO")
                .start(migrarPessoaDestinoStepImp)
                .next(migrarUsuarioDestinoStepImp)
                .build();
    }
}
