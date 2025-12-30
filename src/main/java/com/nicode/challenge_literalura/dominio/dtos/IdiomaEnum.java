package com.nicode.challenge_literalura.dominio.dtos;

public enum IdiomaEnum {
    ESPANOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt");

    private String codigo;

    IdiomaEnum(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
