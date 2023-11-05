package br.com.bln.estudospringbatch.domain.batchs.migrarusuariodestino;

import br.com.bln.estudospringbatch.adapter.gateway.repository.destino.UsuarioDestinoRepository;
import br.com.bln.estudospringbatch.adapter.gateway.repository.origem.PessoaRepository;
import br.com.bln.estudospringbatch.domain.entity.destino.UsuarioDestinoEntity;
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
public class UsuarioDestinoStepConfiguration {

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Autowired
    protected UsuarioDestinoProcessor usuarioDestinoProcessor;

    @Bean(name = "stepMigrarUsuarioDestino")
    public Step stepMigrarUsuarioDestino(RepositoryItemReader usuarioDestinoRepositoryReader, RepositoryItemWriter usuarioDestinoRepositoryWriter) {
        return stepBuilderFactory
                .get("STEP_MIGRAR_USUARIO_DESTINO")
                .<PessoaEntity, UsuarioDestinoEntity>chunk(3)
                .reader(usuarioDestinoRepositoryReader)
                .processor(usuarioDestinoProcessor)
                .writer(usuarioDestinoRepositoryWriter)
                .build();
    }

    @Bean(name = "usuarioDestinoRepositoryReader")
    public RepositoryItemReader<PessoaEntity> usuarioDestinoRepositoryReader(PessoaRepository pessoaRepository) {
        Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);

        return new RepositoryItemReaderBuilder<PessoaEntity>()
                .name("usuarioDestinoRepositoryReader")
                .repository(pessoaRepository)
                .methodName("buscarTodasPorId")
                .sorts(sorts)
                .build();
    }

    @Bean(name = "usuarioDestinoRepositoryWriter")
    public RepositoryItemWriter<UsuarioDestinoEntity> usuarioDestinoRepositoryWriter(UsuarioDestinoRepository usuarioDestinoRepository) {
        return new RepositoryItemWriterBuilder<UsuarioDestinoEntity>()
                .repository(usuarioDestinoRepository)
                .build();
    }
}
