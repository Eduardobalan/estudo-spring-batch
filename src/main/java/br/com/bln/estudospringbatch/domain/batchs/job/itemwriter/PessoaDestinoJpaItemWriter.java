package br.com.bln.estudospringbatch.domain.batchs.job.itemwriter;

import br.com.bln.estudospringbatch.domain.entity.destino.PessoaDestinoEntity;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class PessoaDestinoJpaItemWriter extends JpaItemWriter<PessoaDestinoEntity> implements InitializingBean {

    @Autowired
    @Qualifier("destinoEntityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setEntityManagerFactory(entityManagerFactory);
        super.afterPropertiesSet();
    }
}
