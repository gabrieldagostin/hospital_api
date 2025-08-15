package com.hospital.mateus.curso.medico.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(

        @NotNull
        Long id,

        String nome,

        Double salario
) {
}
