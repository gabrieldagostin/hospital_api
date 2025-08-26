package com.hospital.mateus.curso.paciente.dto;

import jakarta.validation.constraints.NotNull;

public record DadosRemoverMedicoPaciente(

        @NotNull
        Long pacienteId

) {
}
