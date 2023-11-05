package br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida;

import br.com.bln.estudospringbatch.domain.batchs.migrarusuariodestino.UsuarioDestinoProcessor;
import br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida.listener.CicloDeVidaChunkListener;
import br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida.listener.CicloDeVidaItemProcessListener;
import br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida.listener.CicloDeVidaItemReadListener;
import br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida.listener.CicloDeVidaItemWriteListener;
import br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida.listener.CicloDeVidaStepExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CicloDeVidaStepConfiguration {

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Autowired
    protected UsuarioDestinoProcessor usuarioDestinoProcessor;

    @Bean(name = "stepCicloDeVidaTasklet")
    public Step stepCicloDeVidaTasklet(CicloDeVidaTasklet cicloDeVidaTasklet) {
        return stepBuilderFactory
                .get("STEP_CICLO_DE_VIDA_TASKLET")
                .tasklet(cicloDeVidaTasklet)
                .build();
    }

    @Bean(name = "stepCicloDeVidaReaderProcessorWriter")
    public Step stepCicloDeVidaReaderProcessorWriter(RepositoryItemReader cicloDeVidaItemReader,
                                                     ItemProcessor cicloDeVidaProcessor,
                                                     CicloDeVidaItemWriter cicloDeVidaItemWriter) {

        SimpleStepBuilder simpleStepBuilder = stepBuilderFactory
                .get("STEP_MIGRAR_USUARIO_DESTINO")
                .chunk(5)
                .reader(cicloDeVidaItemReader)
                .processor(cicloDeVidaProcessor)
                .writer(cicloDeVidaItemWriter);

        simpleStepBuilder.listener(new CicloDeVidaStepExecutionListener());
        simpleStepBuilder.listener(new CicloDeVidaChunkListener());
        simpleStepBuilder.listener(new CicloDeVidaItemReadListener());
        simpleStepBuilder.listener(new CicloDeVidaItemProcessListener());
        simpleStepBuilder.listener(new CicloDeVidaItemWriteListener());


        return simpleStepBuilder.build();
    }
}
