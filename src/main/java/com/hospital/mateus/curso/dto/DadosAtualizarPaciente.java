package com.hospital.mateus.curso.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPaciente(

        @NotNull
        Long id,

        String nome
) {

}
