package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.visualizarciclodevida.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CicloDeVidaStepExecutionListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("### Ciclo de vida -> CicloDeVidaStepListener -> beforeStep");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("### Ciclo de vida -> CicloDeVidaStepListener -> afterStep");
        return ExitStatus.COMPLETED;
    }
}
