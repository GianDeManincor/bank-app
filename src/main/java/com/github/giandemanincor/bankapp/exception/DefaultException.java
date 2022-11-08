package com.github.giandemanincor.bankapp.exception;

public class DefaultException extends Exception {

    private int codigo;
    private String mensagem;

    public DefaultException(int codigo, String mensagem) {
        this.codigo = this.codigo;
        this.mensagem = this.mensagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
