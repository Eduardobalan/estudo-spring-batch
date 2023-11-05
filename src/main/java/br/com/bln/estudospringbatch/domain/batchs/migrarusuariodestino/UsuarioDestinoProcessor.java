package br.com.bln.estudospringbatch.domain.batchs.migrarusuariodestino;

import br.com.bln.estudospringbatch.domain.entity.destino.UsuarioDestinoEntity;
import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UsuarioDestinoProcessor implements ItemProcessor<PessoaEntity, UsuarioDestinoEntity> {

    @Override
    public UsuarioDestinoEntity process(PessoaEntity pessoaEntity) throws Exception {
        return UsuarioDestinoEntity.builder()
                .id(pessoaEntity.getId())
                .login(pessoaEntity.getApelido())
                .senha(pessoaEntity.getSenha())
                .build();
    }
}
