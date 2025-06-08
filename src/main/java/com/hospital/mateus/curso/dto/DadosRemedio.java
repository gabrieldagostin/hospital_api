package com.hospital.mateus.curso.dto;

import jakarta.validation.constraints.NotNull;

public record DadosRemedio(

        @NotNull
        Long id,

        String nome
) {
}
