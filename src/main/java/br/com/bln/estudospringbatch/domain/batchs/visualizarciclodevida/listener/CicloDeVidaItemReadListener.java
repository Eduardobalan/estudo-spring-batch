package br.com.bln.estudospringbatch.domain.batchs.visualizarciclodevida.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemReadListener;

@Log4j2
public class CicloDeVidaItemReadListener implements ItemReadListener {

    @Override
    public void beforeRead() {
        log.info("### Ciclo de vida -> CicloDeVidaItemReadListener -> beforeRead");
    }


    @Override
    public void afterRead(Object o) {
        log.info("### Ciclo de vida -> CicloDeVidaItemReadListener -> afterRead");
    }

    @Override
    public void onReadError(Exception e) {
        log.info("### Ciclo de vida -> CicloDeVidaItemReadListener -> onReadError");
    }
}
