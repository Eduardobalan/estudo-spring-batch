package br.com.bln.estudospringbatch.domain.batchs.job.step;

import br.com.bln.estudospringbatch.domain.batchs.job.itemreader.PessoaOrigemItemReader;
import br.com.bln.estudospringbatch.domain.batchs.job.itemwriter.PessoaDestinoLogItemWriter;
import br.com.bln.estudospringbatch.domain.batchs.job.itemwriter.PessoaDestinoRepositoryItemWriter;
import br.com.bln.estudospringbatch.domain.batchs.jobsisolados.migrarpessoa.UsuarioDestinoProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MigrarPessoaCompositeDestinoStep {

    public static final int CHUNK_SIZE = 20000;

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Autowired
    protected UsuarioDestinoProcessor usuarioDestinoProcessor;

    @Bean
    public Step migrarPessoaAndUsuarioCompositeDestinoStepImp(PessoaOrigemItemReader pessoaOrigemItemReader,
                                                              ItemProcessor pessoaOrigemToPessoaOrigemProcessor,
                                                              ItemProcessor pessoaOrigemToPessoaDestinoProcessor,
                                                              PessoaDestinoLogItemWriter pessoaDestinoLogItemWriter,
                                                              PessoaDestinoRepositoryItemWriter pessoaDestinoRepositoryItemWriter) {

        CompositeItemProcessor compositeItemProcessor = new CompositeItemProcessor();
        compositeItemProcessor.setDelegates(
                Arrays.asList(pessoaOrigemToPessoaOrigemProcessor, pessoaOrigemToPessoaDestinoProcessor)
        );

        CompositeItemWriter compositeItemWriter = new CompositeItemWriter();
        compositeItemWriter.setDelegates(
                Arrays.asList(pessoaDestinoRepositoryItemWriter, pessoaDestinoLogItemWriter)
        );

        SimpleStepBuilder simpleStepBuilder = stepBuilderFactory
                .get("MIGRAR_PESSOA_COMPOSITE_STEP")
                .chunk(CHUNK_SIZE)
                .reader(pessoaOrigemItemReader)
                .processor(compositeItemProcessor)
                .writer(compositeItemWriter);

        return simpleStepBuilder.build();
    }
}
