package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.utilizandocontext;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.JobSynchronizationManager;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UtilizandoContextFinalTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String a = (String) chunkContext.getStepContext().getStepExecution().getExecutionContext().get("UtilizandoContextTasklet");
        log.info("### UtilizandoContextFinalTasklet (Step 3) -> tentando recuperar valor da variavel 'Step1' salvo no stepExecutionContext = " + a);

        String b = (String) JobSynchronizationManager.getContext().getJobExecution().getExecutionContext().get("UtilizandoContextTasklet");
        log.info("### UtilizandoContextFinalTasklet (job 1) -> tentando recuperar valor da variavel 'Job1' salvo no jobExecutionContext = " + b);

        return RepeatStatus.FINISHED;
    }
}
