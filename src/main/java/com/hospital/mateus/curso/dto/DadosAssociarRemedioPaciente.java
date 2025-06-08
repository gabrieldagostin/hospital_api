package com.hospital.mateus.curso.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAssociarRemedioPaciente(

        @NotNull Long paciente_id,

        @NotNull Long remedio_id
) {
}
