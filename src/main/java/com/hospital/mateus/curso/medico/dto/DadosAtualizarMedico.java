package com.hospital.mateus.curso.medico.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record DadosAtualizarMedico(

        @NotNull
        UUID id,

        String nome,

        BigDecimal salario
) {}
