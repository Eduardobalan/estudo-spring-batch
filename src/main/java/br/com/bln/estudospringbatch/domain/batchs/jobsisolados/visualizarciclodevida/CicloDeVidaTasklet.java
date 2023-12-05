package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.visualizarciclodevida;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CicloDeVidaTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("### Ciclo de vida -> CicloDeVidaTasklet");
        return RepeatStatus.FINISHED;
    }
}
