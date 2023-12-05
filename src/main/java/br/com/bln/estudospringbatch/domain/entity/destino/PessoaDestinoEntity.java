package br.com.bln.estudospringbatch.domain.entity.destino;

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
@Builder
@Entity
@DynamicUpdate
@Table(name = "tb_pessoa_destino")
@AttributeOverride(name = "dataCadastro", column = @Column(name = "PS_DTHR_CADASTRO"))
@AttributeOverride(name = "dataAlteracao", column = @Column(name = "PS_DTHR_ALTERACAO"))
@AttributeOverride(name = "usuarioCadastro", column = @Column(name = "PS_USUARIO_CADASTRO"))
@AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "PS_USUARIO_ALTERACAO"))
public class PessoaDestinoEntity {

    @Id
    @Column(name = "PS_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PS_NOME", nullable = false)
    private String nome;

    @Column(name = "PS_APELIDO")
    private String apelido;

    @Column(name = "PS_ID_ORIGEM")
    private Long idOrigem;
}
