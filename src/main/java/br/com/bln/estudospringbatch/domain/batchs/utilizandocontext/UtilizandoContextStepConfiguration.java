package br.com.bln.estudospringbatch.domain.batchs.utilizandocontext;

import br.com.bln.estudospringbatch.domain.batchs.migrarusuariodestino.UsuarioDestinoProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilizandoContextStepConfiguration {

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
                .chunk(5)
                .reader(utilizandoContextItemReader)
                .processor(utilizandoContextProcessor)
                .writer(utilizandoContextItemWriter)
                .build();
    }
}
