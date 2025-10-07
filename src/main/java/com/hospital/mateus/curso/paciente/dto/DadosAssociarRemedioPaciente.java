package com.hospital.mateus.curso.paciente.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAssociarRemedioPaciente(

        @NotNull
        Long paciente_id,

        @NotNull
        Long remedio_id
) {}
