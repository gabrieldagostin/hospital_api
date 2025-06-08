package com.hospital.mateus.curso.dto;

import jakarta.validation.constraints.NotNull;

public record DadosRemoverMedicoPaciente(

        @NotNull
        Long paciente_id

) {
}
