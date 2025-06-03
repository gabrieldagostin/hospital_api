package com.hospital.mateus.curso.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(
        @NotNull
        long id,
        String nome,
        Double salario
) {
}
