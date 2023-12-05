package br.com.bln.estudospringbatch.domain.batchs.job.tasklet;

import br.com.bln.estudospringbatch.adapter.gateway.repository.destino.UsuarioDestinoRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDestinoCleanTasklet implements Tasklet {

    @Autowired
    private UsuarioDestinoRepository repository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        repository.deleteAll();
        return RepeatStatus.FINISHED;
    }
}
