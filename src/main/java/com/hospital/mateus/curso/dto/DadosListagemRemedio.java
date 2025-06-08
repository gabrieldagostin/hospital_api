package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.enums.Laboratorio;
import com.hospital.mateus.curso.model.Remedio;
import com.hospital.mateus.curso.model.enums.Via;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosListagemRemedio(

        @NotNull
        Long id,

        String nome,

        Via via,

        String lote,

        Laboratorio laboratorio,

        LocalDate validade) {

    public DadosListagemRemedio(Remedio remedio) {
        this(
                remedio.getId(),
                remedio.getNome(),
                remedio.getVia(),
                remedio.getLote(),
                remedio.getLaboratorio(),
                remedio.getValidade()
        );
    }
}

