package com.hospital.mateus.curso.core.enums;

import lombok.Getter;

@Getter
public enum Laboratorio {

    EMS(0, "Ems"),
    EUROFARMA(1, "Eurofarma"),
    ACHE(2, "Ache"),
    HYPERA_PHARMA(3, "Hypera_Pharma"),
    NEO_QUIMICA(4, "Neo_Quimica"),
    MEDLEY(5, "Medley"),
    SANOFI(6, "Sanofi"),
    PFIZER(7, "Pfizer"),
    NOVARTIS(8, "Novartis"),
    BAYER(9, "Bayer"),
    ROCHE(10, "Roche"),
    GSK(11, ""),
    ASTRAZENECA(12, ""),
    MERCK(13, ""),
    BOEHRINGER_INGELHEIM(14, "");

    private Integer cod;
    private String descricao;

    Laboratorio(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Laboratorio toEnum(Integer cod) {
        if (cod == null) return null;
        for (Laboratorio laboratorio : Laboratorio.values()) {
            if (cod.equals(laboratorio.cod)) return laboratorio;
        }
        throw new IllegalArgumentException("Laborátorio inválido");
    }
}
