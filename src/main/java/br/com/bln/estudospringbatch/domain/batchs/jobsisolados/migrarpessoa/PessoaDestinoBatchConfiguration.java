package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.migrarpessoa;

import br.com.bln.estudospringbatch.adapter.gateway.repository.destino.PessoaDestinoRepository;
import br.com.bln.estudospringbatch.adapter.gateway.repository.origem.PessoaRepository;
import br.com.bln.estudospringbatch.domain.batchs.job.tasklet.PessoaDestinoCleanTasklet;
import br.com.bln.estudospringbatch.domain.entity.destino.PessoaDestinoEntity;
import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import org.springframework.batch.core.Step;
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
public class PessoaDestinoBatchConfiguration {

    public static final int CHUNK_SIZE = 20000;

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Autowired
    protected PessoaDestinoProcessor pessoaDestinoProcessor;

    @Autowired
    protected PessoaDestinoCleanTasklet pessoaDestinoCleanTasklet;

    @Bean
    public Step stepMigrarPessoaDestino(RepositoryItemReader pessoaDestinoRepositoryReader, RepositoryItemWriter pessoaDestinoRepositoryWriter) {
        return stepBuilderFactory
                .get("STEP_MIGRAR_PESSOA_DESTINO")
                .<PessoaEntity, PessoaDestinoEntity>chunk(CHUNK_SIZE)
                .reader(pessoaDestinoRepositoryReader)
                .processor(pessoaDestinoProcessor)
                .writer(pessoaDestinoRepositoryWriter)
                .build();
    }

    @Bean
    public RepositoryItemReader<PessoaEntity> pessoaDestinoRepositoryReader(PessoaRepository pessoaRepository) {
        Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);

        return new RepositoryItemReaderBuilder<PessoaEntity>()
                .name("pessoaDestinoRepositoryReader")
                .repository(pessoaRepository)
                .methodName("buscarTodasPorId")
                .pageSize(CHUNK_SIZE)
                .sorts(sorts)
                .build();
    }

    @Bean
    public RepositoryItemWriter<PessoaDestinoEntity> pessoaDestinoRepositoryWriter(PessoaDestinoRepository pessoaDestinoRepository) {
        return new RepositoryItemWriterBuilder<PessoaDestinoEntity>()
                .repository(pessoaDestinoRepository)
                .build();
    }
}
