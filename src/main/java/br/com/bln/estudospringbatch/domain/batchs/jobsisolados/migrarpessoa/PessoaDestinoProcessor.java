package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.migrarpessoa;

import br.com.bln.estudospringbatch.domain.entity.destino.PessoaDestinoEntity;
import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PessoaDestinoProcessor implements ItemProcessor<PessoaEntity, PessoaDestinoEntity> {

    @Override
    public PessoaDestinoEntity process(PessoaEntity pessoaEntity) throws Exception {
        return PessoaDestinoEntity.builder()
                .id(pessoaEntity.getId())
                .nome(pessoaEntity.getNome())
                .apelido(pessoaEntity.getApelido())
                .build();
    }
}
