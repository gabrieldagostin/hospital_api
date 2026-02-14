package com.hospital.mateus.curso.remedio.dto;

import com.hospital.mateus.curso.core.enums.Laboratorio;
import com.hospital.mateus.curso.remedio.model.Remedio;
import com.hospital.mateus.curso.core.enums.Via;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DadosListagemRemedio(

        @NotNull
        UUID id,

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

