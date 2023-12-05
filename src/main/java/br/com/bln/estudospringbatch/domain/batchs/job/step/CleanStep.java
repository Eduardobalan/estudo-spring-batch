package br.com.bln.estudospringbatch.domain.batchs.job.step;


import br.com.bln.estudospringbatch.domain.batchs.job.tasklet.PessoaDestinoCleanTasklet;
import br.com.bln.estudospringbatch.domain.batchs.job.tasklet.UsuarioDestinoCleanTasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CleanStep {

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step cleanStepImp(
            PessoaDestinoCleanTasklet pessoaDestinoCleanTasklet,
            UsuarioDestinoCleanTasklet usuarioDestinoCleanTasklet) {
        return stepBuilderFactory
                .get("PESSOA_DESTINO_CLEAN_STEP")
                .tasklet(pessoaDestinoCleanTasklet)
                .tasklet(usuarioDestinoCleanTasklet)
                .build();
    }
}
