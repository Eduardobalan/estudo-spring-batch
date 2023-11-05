package br.com.bln.estudospringbatch.domain.entity.origem;

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
@Table(name = "TB_PESSOA")
@AttributeOverride(name = "dataCadastro", column = @Column(name = "PS_DTHR_CADASTRO"))
@AttributeOverride(name = "dataAlteracao", column = @Column(name = "PS_DTHR_ALTERACAO"))
@AttributeOverride(name = "usuarioCadastro", column = @Column(name = "PS_USUARIO_CADASTRO"))
@AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "PS_USUARIO_ALTERACAO"))
public class PessoaEntity extends GenericEntity {

    @Id
    @Column(name = "PS_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PS_NOME", nullable = false)
    private String nome;

    @Column(name = "PS_APELIDO")
    private String apelido;

    @Column(name = "PS_LOGIN")
    private String login;

    @Column(name = "PS_SENHA")
    private String senha;

}
