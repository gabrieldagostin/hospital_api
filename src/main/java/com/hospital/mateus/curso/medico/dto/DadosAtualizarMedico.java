package com.hospital.mateus.curso.medico.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizarMedico(

        @NotNull
        Long id,

        String nome,

        BigDecimal salario
) {
}
