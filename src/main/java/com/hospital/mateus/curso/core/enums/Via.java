package com.hospital.mateus.curso.core.enums;

import lombok.Getter;

@Getter
public enum Via {

    ORAL(0, "Oral"),
    SUBLINGUAL(1, "Sublingual"),
    RETAL(2, "Retal"),
    INJETAVEL(3, "Injetavel"),
    INTRAVENOSA(4, "Intravenosa"),
    INTRAMUSCULAR(5, "Intramuscular"),
    SUBCUTANEA(6, "Subcutanea"),
    INALATORIA(7, "Inalatoria"),
    NASAL(8, "Nasal"),
    TOPICA(9, "Topica"),
    OCULAR(10, "Ocular"),
    OTICA(11, "Otica"),
    TRANSDERMICA(12, "Transdermica"),
    EPIDURAL(13, "Epidural");

    private Integer cod;
    private String description;

    Via(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Via toEnum(Integer cod) {
        if (cod == null) return null;
        for (Via via : Via.values()) {
            if (via.equals(cod)) return via;
        }
        throw new IllegalArgumentException("Via inválida");
    }
}
