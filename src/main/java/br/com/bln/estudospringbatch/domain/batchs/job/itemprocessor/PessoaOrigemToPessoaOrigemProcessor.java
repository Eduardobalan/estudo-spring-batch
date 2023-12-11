package br.com.bln.estudospringbatch.domain.batchs.job.itemprocessor;

import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PessoaOrigemToPessoaOrigemProcessor implements ItemProcessor<PessoaEntity, PessoaEntity> {

        @Override
        public PessoaEntity process(PessoaEntity pessoaEntity) throws Exception {

            return PessoaEntity.builder()
                    .nome(pessoaEntity.getNome())
                    .apelido("@"+pessoaEntity.getApelido())
                    .login(pessoaEntity.getLogin())
                    .senha(pessoaEntity.getSenha())
                    .build();
        }
}
