package com.github.giandemanincor.bankapp.model.enums;

import java.util.Arrays;

public enum TipoTransacao {

    TRANSAFERENCIA(1), DEPOSITO(2);

    private int code;

    private TipoTransacao(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TipoTransacao valueOf(int code) {
        return Arrays.stream(TipoTransacao.values()).filter(tipo -> tipo.getCode() == code).findFirst().orElseThrow(
                () -> new IllegalArgumentException("Código do TipoTransação inválido."));
    }
}
