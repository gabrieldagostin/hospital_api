package com.hospital.mateus.curso.core.enums;

import lombok.Getter;

@Getter
public enum Especialidade {

    CLINICO_GERAL(0, "Clinico_Geral"),
    PEDIATRA(1, "Pediatra"),
    CARDIOLOGISTA(2, "Cardiologista"),
    DERMATOLOGISTA(3, "Dermatologista"),
    ORTOPEDISTA(4, "Ortopedista"),
    GINECOLOGISTA(5, "Ginecologista"),
    OBSTETRA(6, "Obstetra"),
    PSIQUIATRA(7, "Psiquiatra"),
    NEUROLOGISTA(8, "Neurologista"),
    UROLOGISTA(9, "Urulogista"),
    OFTALMOLOGISTA(10, "Oftamologista"),
    OTORRINOLARINGOLOGISTA(11, "Otorrinolaringonologista"),
    ENDOCRINOLOGISTA(12, "Endocrinologista"),
    REUMATOLOGISTA(13, "Reumatologista"),
    GASTROENTEROLOGISTA(14, "Gastroenterologista"),
    ONCOLOGISTA(15, "Oncologista"),
    HEMATOLOGISTA(16, "Hematologista"),
    NEFROLOGISTA(17, "Nefrologista"),
    INFECTOLOGISTA(18, "Infectologista"),
    ANGIOLOGISTA(19, "Angiologista"),
    HEPATOLOGISTA(20, "Hepatologista"),
    PNEUMOLOGISTA(21, "Peneumologista"),
    CIRURGIAO_GERAL(22, "Cirurgiao_Geral"),
    CIRURGIAO_CARDIOVASCULAR(23, "Cirurgiao_Cardiovascular"),
    CIRURGIAO_PLASTICO(24, "Cirurgiao_Plastico"),
    CIRURGIAO_NEUROLOGICO(25, "Cirurgiao_Neurologico"),
    CIRURGIAO_TORACICO(26, "Cirurgiao_Toracico"),
    CIRURGIAO_VASCULAR(27, "Cirurgiao_Vascular"),
    ANESTESIOLOGISTA(28, "Anestesiologista"),
    RADIOLOGISTA(29, "Radiologista"),
    GENETICISTA(30, "Geneticista"),
    MEDICO_DO_TRABALHO(31, "Medico_Do_Trabalho"),
    MEDICO_ESPORTIVO(32, "Medico_Esportivo"),
    MEDICO_LEGAL(33, "Medico_Legal"),
    GERIATRA(34, "Geniatra"),
    ALERGOLOGISTA(35, "Alergologista"),
    IMUNOLOGISTA(36, "Imunologista"),
    PATOLOGISTA(37, "Patologista"),
    NUTROLOGO(38, "Nutrologo");

    private Integer cod;
    private String description;

    Especialidade(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Especialidade toEnum(Integer cod) {
        if (cod == null) return null;
        for (Especialidade especialidade: Especialidade.values()) {
            if (cod.equals(especialidade.cod)) return especialidade;
        }
        throw new IllegalArgumentException("Expecialidade Inválida");
    }
}
