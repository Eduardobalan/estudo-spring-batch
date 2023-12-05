package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.utilizandocontext;

import br.com.bln.estudospringbatch.domain.entity.destino.UsuarioDestinoEntity;
import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UtilizandoContextProcessor implements ItemProcessor<PessoaEntity, UsuarioDestinoEntity> {

    @Override
    public UsuarioDestinoEntity process(PessoaEntity pessoaEntity) throws Exception {
        return UsuarioDestinoEntity.builder()
                .id(pessoaEntity.getId())
                .login(pessoaEntity.getApelido())
                .senha(pessoaEntity.getSenha())
                .build();
    }
}
