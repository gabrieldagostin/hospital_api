package com.hospital.mateus.curso.paciente.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosAtualizarPaciente(

        @NotNull
        UUID id,

        String nome
) {}
