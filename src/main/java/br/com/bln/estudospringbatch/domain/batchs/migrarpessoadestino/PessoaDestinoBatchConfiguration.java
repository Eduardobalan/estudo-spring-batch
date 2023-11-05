package br.com.bln.estudospringbatch.domain.batchs.migrarpessoadestino;

import br.com.bln.estudospringbatch.domain.entity.destino.PessoaDestinoEntity;
import br.com.bln.estudospringbatch.adapter.gateway.repository.destino.PessoaDestinoRepository;
import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import br.com.bln.estudospringbatch.adapter.gateway.repository.origem.PessoaRepository;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class PessoaDestinoBatchConfiguration {

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Autowired
    protected PessoaDestinoProcessor pessoaDestinoProcessor;

    @Autowired
    protected PessoaDestinoCleanTasklet pessoaDestinoCleanTasklet;

    @Bean(name = "stepPessoaDestinoCleanTask")
    public Step stepPessoaDestinoCleanTask() {
        return stepBuilderFactory
                .get("STEP_PESSOA_DESTINO_CLEAN_TASK")
                .tasklet(pessoaDestinoCleanTasklet)
                .build();
    }

    @Bean(name = "stepMigrarPessoaDestino")
    public Step stepMigrarPessoaDestino(RepositoryItemReader pessoaDestinoRepositoryReader, RepositoryItemWriter pessoaDestinoRepositoryWriter) {
        return stepBuilderFactory
                .get("STEP_MIGRAR_PESSOA_DESTINO")
                .<PessoaEntity, PessoaDestinoEntity>chunk(2)
                .reader(pessoaDestinoRepositoryReader)
                .processor(pessoaDestinoProcessor)
                .writer(pessoaDestinoRepositoryWriter)
                .build();
    }

    @Bean(name = "pessoaDestinoRepositoryReader")
    public RepositoryItemReader<PessoaEntity> pessoaDestinoRepositoryReader(PessoaRepository pessoaRepository) {
        Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);

        return new RepositoryItemReaderBuilder<PessoaEntity>()
                .name("pessoaDestinoRepositoryReader")
                .repository(pessoaRepository)
                .methodName("buscarTodasPorId")
                .sorts(sorts)
                .build();
    }

    @Bean(name = "pessoaDestinoRepositoryWriter")
    public RepositoryItemWriter<PessoaDestinoEntity> pessoaDestinoRepositoryWriter(PessoaDestinoRepository pessoaDestinoRepository) {
        return new RepositoryItemWriterBuilder<PessoaDestinoEntity>()
                .repository(pessoaDestinoRepository)
                .build();
    }
}
