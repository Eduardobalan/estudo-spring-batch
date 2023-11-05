package br.com.bln.estudospringbatch.application.commandline;


import br.com.bln.estudospringbatch.application.utils.FileHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Log4j2
@Component
@Order(2)
public class MigrarCargaOrigemCommandLineRunner implements CommandLineRunner {

    @PersistenceContext(unitName = "persistenceUnitOrigem")
    private EntityManager entityManagerOrigem;

    @Override
    @Transactional(value = "origemTransactionManager")
    public void run(String... args) throws Exception {
        Boolean existeCargaOrigem = (Boolean) entityManagerOrigem.createNativeQuery("SELECT count(*) > 0 FROM tb_pessoa").getSingleResult();

        if (!existeCargaOrigem) {
            log.info("## Iniciando SQL para inserir registros DB_ORIGEM...");
            try {
                String scriptTabelas = FileHelper.getFileInResources("db/banco-origem-carga-inicial.sql");
                entityManagerOrigem.createNativeQuery(scriptTabelas).executeUpdate();
            } catch (Exception e) {
                log.error("## Erro ao migrar scrips para o banco de origem.");
                e.printStackTrace();
                throw e;
            }
        }
    }
}
