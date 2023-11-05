package br.com.bln.estudospringbatch.domain.entity.destino;

import br.com.bln.estudospringbatch.domain.entity.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@DynamicUpdate
@Table(name = "tb_usuario_destino")
@AttributeOverride(name = "dataCadastro", column = @Column(name = "US_DTHR_CADASTRO"))
@AttributeOverride(name = "dataAlteracao", column = @Column(name = "US_DTHR_ALTERACAO"))
@AttributeOverride(name = "usuarioCadastro", column = @Column(name = "US_USUARIO_CADASTRO"))
@AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "US_USUARIO_ALTERACAO"))
public class UsuarioDestinoEntity extends GenericEntity {

    @Id
    @Column(name = "US_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "US_LOGIN")
    private String login;

    @Column(name = "US_SENHA")
    private String senha;
}
