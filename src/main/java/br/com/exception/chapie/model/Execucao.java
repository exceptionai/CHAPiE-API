package br.com.exception.chapie.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "EXECUCAO")
public class Execucao implements Serializable {

    @Id
    @Column(name = "ID_EXECUCAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOT_EXECUCAO_SEQ")
    @SequenceGenerator(name = "BOT_EXECUCAO_SEQ", sequenceName = "BOT_EXECUCAO_SEQ", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="ID_ACAO")
    @NotNull
    private Acao acao;

    @Column(name = "DATA_EXECUCAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExecucao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Date getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
    }
}
