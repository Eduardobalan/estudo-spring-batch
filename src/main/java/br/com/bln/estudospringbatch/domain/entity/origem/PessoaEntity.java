package br.com.bln.estudospringbatch.domain.entity.origem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

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
public class PessoaEntity {

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
