package br.com.bln.estudospringbatch.domain.batchs.jobsisolados.visualizarciclodevida.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

@Log4j2
public class CicloDeVidaChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        log.info("### Ciclo de vida -> CicloDeVidaChunkListener -> beforeChunk");
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        log.info("### Ciclo de vida -> CicloDeVidaChunkListener -> afterChunk");
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        log.info("### Ciclo de vida -> CicloDeVidaChunkListener -> afterChunkError");
    }
}
