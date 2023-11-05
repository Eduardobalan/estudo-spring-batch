package br.com.bln.estudospringbatch.adapter.gateway.repository.destino;


import br.com.bln.estudospringbatch.domain.entity.destino.UsuarioDestinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDestinoRepository extends JpaRepository<UsuarioDestinoEntity, Long> {

}
