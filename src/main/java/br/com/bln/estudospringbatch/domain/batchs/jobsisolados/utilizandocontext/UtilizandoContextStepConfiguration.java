package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.utilizandocontext;

import br.com.bln.estudospringbatch.domain.batchs.jobsisolados.migrarpessoa.UsuarioDestinoProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilizandoContextStepConfiguration {

    public static final int CHUNK_SIZE = 20000;

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Autowired
    protected UsuarioDestinoProcessor usuarioDestinoProcessor;

    @Bean(name = "stepUtilizandoContextTasklet")
    public Step stepUtilizandoContextTasklet(UtilizandoContextTasklet utilizandoContextTasklet) {
        return stepBuilderFactory
                .get("STEP_UTILIZANDO_CONTEXT_TASKLET")
                .tasklet(utilizandoContextTasklet)
                .build();
    }

    @Bean(name = "stepUtilizandoContextFinalTasklet")
    public Step stepUtilizandoContextFinalTasklet(UtilizandoContextFinalTasklet utilizandoContextFinalTasklet) {
        return stepBuilderFactory
                .get("STEP_UTILIZANDO_CONTEXT_FINAL_TASKLET")
                .tasklet(utilizandoContextFinalTasklet)
                .build();
    }

    @Bean(name = "stepUtilizandoContextReaderProcessorWriter")
    public Step stepUtilizandoContextReaderProcessorWriter(RepositoryItemReader utilizandoContextItemReader,
                                                           ItemProcessor utilizandoContextProcessor,
                                                           UtilizandoContextItemWriter utilizandoContextItemWriter) {
        return stepBuilderFactory
                .get("STEP_UTILIZANDO_CONTEXT")
                .chunk(CHUNK_SIZE)
                .reader(utilizandoContextItemReader)
                .processor(utilizandoContextProcessor)
                .writer(utilizandoContextItemWriter)
                .build();
    }
}
