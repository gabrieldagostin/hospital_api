package com.hospital.mateus.curso.paciente.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPaciente(

        @NotNull
        Long id,

        String nome
) {}
