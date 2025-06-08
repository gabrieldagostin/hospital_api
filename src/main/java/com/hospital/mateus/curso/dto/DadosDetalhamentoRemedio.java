package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.enums.Laboratorio;
import com.hospital.mateus.curso.model.Remedio;
import com.hospital.mateus.curso.model.enums.Via;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosDetalhamentoRemedio(

        @NotNull
        Long id,

        String nome,

        Via via,

        String lote,

        int quantidade,

        LocalDate validade,

        Laboratorio laboratorio,

        boolean ativo
) {

    public DadosDetalhamentoRemedio(Remedio remedio) {
        this(
                remedio.getId(),
                remedio.getNome(),
                remedio.getVia(),
                remedio.getLote(),
                remedio.getQuantidade(),
                remedio.getValidade(),
                remedio.getLaboratorio(),
                remedio.isAtivo());;
    }
}
