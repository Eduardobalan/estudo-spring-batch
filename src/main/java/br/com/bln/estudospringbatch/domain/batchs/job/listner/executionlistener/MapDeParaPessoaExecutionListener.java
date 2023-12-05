package br.com.bln.estudospringbatch.domain.batchs.job.listner.executionlistener;

import br.com.bln.estudospringbatch.adapter.gateway.repository.destino.PessoaDestinoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@Component
@StepScope
public class MapDeParaPessoaExecutionListener implements StepExecutionListener {

    @Autowired
    private PessoaDestinoRepository pessoaDestinoRepository;

    public Map<Long, Long> map;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        if (Objects.isNull(map)) {
            this.map = new ConcurrentHashMap<Long, Long>();

            pessoaDestinoRepository.mapDePara()
                    .stream()
                    .forEach(obj -> {
                        this.map.put((Long) obj[0], (Long) obj[1]);
                    });

            log.info("### StepExecutionListener -> Criando MapDeParaPessoaExecutionListener");
        }

    }

    public Long obterNovoId(Long idDestino) {
        return map.get(idDestino);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }
}
