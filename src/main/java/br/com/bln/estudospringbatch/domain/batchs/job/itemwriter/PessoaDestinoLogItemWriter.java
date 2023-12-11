package br.com.bln.estudospringbatch.domain.batchs.job.itemwriter;

import br.com.bln.estudospringbatch.domain.entity.destino.PessoaDestinoEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class PessoaDestinoLogItemWriter implements ItemWriter<PessoaDestinoEntity> {


    @Override
    public void write(List<? extends PessoaDestinoEntity> list) throws Exception {
        log.info("PessoaDestinoLogItemWriter");
        list.forEach(pessoaDestinoEntity -> {
            log.info(pessoaDestinoEntity.getId());
        });
    }
}
