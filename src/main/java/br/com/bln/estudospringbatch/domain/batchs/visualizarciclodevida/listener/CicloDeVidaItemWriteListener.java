package br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

@Log4j2
public class CicloDeVidaItemWriteListener implements ItemWriteListener {

    @Override
    public void beforeWrite(List list) {
        log.info("### Ciclo de vida -> CicloDeVidaItemWriteListener -> beforeWrite");
    }

    @Override
    public void afterWrite(List list) {
        log.info("### Ciclo de vida -> CicloDeVidaItemWriteListener -> afterWrite");
    }

    @Override
    public void onWriteError(Exception e, List list) {
        log.info("### Ciclo de vida -> CicloDeVidaItemWriteListener -> onWriteError");
    }
}
