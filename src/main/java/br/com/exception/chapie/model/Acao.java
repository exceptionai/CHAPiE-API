package br.com.exception.chapie.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ACAO")
public class Acao  implements Serializable {

    public Acao(){

    }

    public Acao(Integer id){
        this.id = id;
    }

    public Acao(Integer id, Boolean ativo){
        this.id = id;
        this.ativo = ativo;
    }

    @Id
    @Column(name = "ID_ACAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOT_ACAO_SEQ")
    @SequenceGenerator(name = "BOT_ACAO_SEQ", sequenceName = "BOT_ACAO_SEQ", allocationSize = 1)
    private Integer id;

    @Column(length = 40)
    @NotNull
    private String nome;

    @Column(length = 100)
    @NotNull
    private String descricao;

    @OneToMany(mappedBy="acao")
    private List<Execucao> execucao;

    private Boolean ativo;
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


}
