package com.github.giandemanincor.bankapp.exception;

public class MensagemErro {

    private final int codigo;
    private final String mensagem;

    public MensagemErro(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public String getMesagem() {
        return mensagem;
    }

    public int getCodigo() {
        return codigo;
    }
}
