package br.com.bln.estudospringbatch.adapter.gateway.repository.destino;


import br.com.bln.estudospringbatch.domain.entity.destino.PessoaDestinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaDestinoRepository extends JpaRepository<PessoaDestinoEntity, Long> {

}
