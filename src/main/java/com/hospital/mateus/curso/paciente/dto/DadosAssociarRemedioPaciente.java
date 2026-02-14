package com.hospital.mateus.curso.paciente.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosAssociarRemedioPaciente(

        @NotNull
        UUID paciente_id,

        @NotNull
        UUID remedio_id
) {}
