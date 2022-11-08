package com.github.giandemanincor.bankapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.giandemanincor.bankapp.model.enums.TipoTransacao;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_TRANSACAO")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "destinatario_id")
    @JsonProperty("destinatario_id")
    private Integer destinatarioId;

    @Column(name = "remetente_id")
    @JsonProperty("remetente_id")
    private Integer remetenteId;

    @Enumerated(EnumType.ORDINAL)
    private TipoTransacao tipo;

    private BigDecimal valor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Integer destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public Integer getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(Integer remetenteId) {
        this.remetenteId = remetenteId;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
