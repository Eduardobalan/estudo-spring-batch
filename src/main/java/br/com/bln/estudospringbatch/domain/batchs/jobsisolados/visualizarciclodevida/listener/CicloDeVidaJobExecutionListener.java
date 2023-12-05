package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.visualizarciclodevida.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;


@Log4j2
public class CicloDeVidaJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("### Ciclo de vida -> CicloDeVidaJobListener -> beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("### Ciclo de vida -> CicloDeVidaProcessor -> afterJob");
    }
}
