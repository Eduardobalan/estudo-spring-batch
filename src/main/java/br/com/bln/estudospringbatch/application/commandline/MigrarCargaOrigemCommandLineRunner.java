package br.com.bln.estudospringbatch.application.commandline;


import br.com.bln.estudospringbatch.adapter.gateway.repository.origem.PessoaRepository;
import br.com.bln.estudospringbatch.application.utils.FileHelper;
import br.com.bln.estudospringbatch.domain.entity.origem.PessoaEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Order(2)
public class MigrarCargaOrigemCommandLineRunner implements CommandLineRunner {

    @PersistenceContext(unitName = "persistenceUnitOrigem")
    private EntityManager entityManagerOrigem;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    @Transactional(value = "origemTransactionManager")
    public void run(String... args) throws Exception {
        Boolean existeCargaOrigem = (Boolean) entityManagerOrigem.createNativeQuery("SELECT count(*) > 0 FROM tb_pessoa").getSingleResult();

        inserirRegistroArquivoMigracao(existeCargaOrigem);
        inserirRegistrosAleatorios(existeCargaOrigem, 999945);
    }

    private void inserirRegistroArquivoMigracao(Boolean existeCargaOrigem) {
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

    private void inserirRegistrosAleatorios(Boolean existeCargaOrigem, int quantidadeRegistrosParaInserir) {
        if (!existeCargaOrigem) {
            log.info(String.format("## Iniciando inserção de '%s' no banco!", quantidadeRegistrosParaInserir));
            List<PessoaEntity> pessoaEntityList = new ArrayList<>();

            for (int i = 0; i < quantidadeRegistrosParaInserir; i++) {
                pessoaEntityList.add(
                        PessoaEntity.builder()
                                .nome(UUID.randomUUID().toString())
                                .login(UUID.randomUUID().toString())
                                .senha(UUID.randomUUID().toString())
                                .apelido(UUID.randomUUID().toString())
                                .build()
                );
            }

            pessoaRepository.saveAll(pessoaEntityList);
            log.info("## Inserção concluida!");
        }
    }
}
