package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.visualizarciclodevida.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemProcessListener;

@Log4j2
public class CicloDeVidaItemProcessListener implements ItemProcessListener {

    @Override
    public void beforeProcess(Object o) {
        log.info("### Ciclo de vida -> CicloDeVidaItemProcessListener -> beforeProcess");
    }

    @Override
    public void afterProcess(Object o, Object o2) {
        log.info("### Ciclo de vida -> CicloDeVidaItemProcessListener -> afterProcess");
    }

    @Override
    public void onProcessError(Object o, Exception e) {
        log.info("### Ciclo de vida -> CicloDeVidaItemProcessListener -> onProcessError");
    }
}
