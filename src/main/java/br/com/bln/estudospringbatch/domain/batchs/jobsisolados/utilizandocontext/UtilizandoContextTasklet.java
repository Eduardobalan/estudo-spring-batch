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
public class UtilizandoContextTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        /*
         * O método chunkContext.getStepContext().getStepExecution().getExecutionContext() obtém o contexto específico do Step em execução. Portanto, os valores inseridos aqui não são compartilhados com outros steps.
         * Estes valores são persistidos no banco de dados na tabela "batch_step_execution_context" sob a entrada correspondente a esta execução do step.
         * Se este step for reiniciado, os valores inseridos no contexto serão recarregados na memória da aplicação.
         * Obs 1: Em vez de utilizar a variável ChunkContext passada como parâmetro, você pode alcançar o mesmo resultado chamando StepSynchronizationManager.getContext().getStepExecution().getExecutionContext().
         */
        chunkContext.getStepContext().getStepExecution().getExecutionContext().put("step1", "Valor Step 1");
        log.info("### UtilizandoContextTasklet (Step 1) -> Inserindo variavel 'step1' no StepExecutionContext com valor 'Valor Step 1'");

        /*
         * O método JobSynchronizationManager.getContext().getJobExecution().getExecutionContext() obtém o contexto do Job, e esse valor é compartilhado entre os steps.
         * Os valores inseridos aqui são persistidos no banco de dados na tabela "batch_job_execution_context" e serão recarregados na memória da aplicação se este job for reiniciado.
         */
        JobSynchronizationManager.getContext().getJobExecution().getExecutionContext().put("UtilizandoContextTasklet", "Valor job 1");
        log.info("### UtilizandoContextTasklet (Step 1) -> Inserindo variavel 'Job1' no JobExecutionContext com valor 'Valor job 1'");

        return RepeatStatus.FINISHED;
    }
}
