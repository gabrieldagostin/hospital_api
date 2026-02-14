package com.hospital.mateus.curso.core.enums;

import lombok.Getter;

@Getter
public enum Sexo {
    MASCULINO(0, "Masculino"),
    FEMININO(1, "Feminino"),
    OUTRO(2, "Outro");

    private Integer cod;
    private String desciption;

    Sexo(Integer cod, String desciption) {
        this.cod = cod;
        this.desciption = desciption;
    }

    public Sexo toEnum(Integer cod) {
        if (cod == null) return null;
        for (Sexo sexo : Sexo.values()) {
            if (sexo.equals(cod)) return sexo;
        }
        throw new IllegalArgumentException("Sexo inválido");
    }
}
