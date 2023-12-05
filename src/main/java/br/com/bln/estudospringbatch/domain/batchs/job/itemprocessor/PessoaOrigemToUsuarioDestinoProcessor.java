package br.com.bln.estudospringbatch.domain.batchs.job.itemprocessor;

import br.com.bln.estudospringbatch.domain.batchs.job.listner.executionlistener.MapDeParaPessoaExecutionListener;
import br.com.bln.estudospringbatch.domain.entity.destino.PessoaDestinoEntity;
import br.com.bln.estudospringbatch.domain.entity.destino.UsuarioDestinoEntity;
import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PessoaOrigemToUsuarioDestinoProcessor implements ItemProcessor<PessoaEntity, UsuarioDestinoEntity> {

    @Autowired
    private MapDeParaPessoaExecutionListener mapDeParaPessoaExecutionListener;

    @Override
    public UsuarioDestinoEntity process(PessoaEntity pessoaEntity) throws Exception {
        PessoaDestinoEntity pessoaDestinoEntity = PessoaDestinoEntity.builder()
                .id(mapDeParaPessoaExecutionListener.obterNovoId(pessoaEntity.getId()))
                .build();

        return UsuarioDestinoEntity.builder()
                .senha(pessoaEntity.getSenha())
                .login(pessoaEntity.getApelido())
                .pessoaDestinoEntity(pessoaDestinoEntity)
                .build();
    }
}
