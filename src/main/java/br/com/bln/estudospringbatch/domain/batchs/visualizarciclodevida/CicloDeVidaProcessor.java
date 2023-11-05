package br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida;

import br.com.bln.estudospringbatch.domain.entity.destino.UsuarioDestinoEntity;
import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CicloDeVidaProcessor implements ItemProcessor<PessoaEntity, UsuarioDestinoEntity> {

    @Override
    public UsuarioDestinoEntity process(PessoaEntity pessoaEntity) throws Exception {
        log.info("### Ciclo de vida -> CicloDeVidaProcessor");
        return UsuarioDestinoEntity.builder()
                .id(pessoaEntity.getId())
                .login(pessoaEntity.getApelido())
                .senha(pessoaEntity.getSenha())
                .build();
    }
}
