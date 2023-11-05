package br.com.bln.estudospringbatch.domain.batchs.migrarpessoadestino;

import br.com.bln.estudospringbatch.adapter.gateway.repository.destino.PessoaDestinoRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaDestinoCleanTasklet implements Tasklet {

    @Autowired
    private PessoaDestinoRepository pessoaDestinoRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        pessoaDestinoRepository.deleteAll();
        return RepeatStatus.FINISHED;
    }
}
