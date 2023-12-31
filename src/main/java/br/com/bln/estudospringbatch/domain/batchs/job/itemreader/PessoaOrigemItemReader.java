package br.com.bln.estudospringbatch.domain.batchs.job.itemreader;

import br.com.bln.estudospringbatch.adapter.gateway.repository.origem.PessoaRepository;
import br.com.bln.estudospringbatch.domain.batchs.jobsisolados.visualizarciclodevida.CicloDeVidaStepConfiguration;
import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Log4j2
@Component
public class PessoaOrigemItemReader extends RepositoryItemReader<PessoaEntity> implements InitializingBean {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        setRepository(pessoaRepository);
        setMethodName("buscarTodasPorId");
        setSort(Collections.singletonMap("id", Sort.Direction.ASC));
        setPageSize(CicloDeVidaStepConfiguration.CHUNK_SIZE);
        super.afterPropertiesSet();
    }

}
