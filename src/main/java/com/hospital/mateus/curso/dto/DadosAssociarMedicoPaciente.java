package com.hospital.mateus.curso.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAssociarMedicoPaciente(

        @NotNull
        Long paciente_id,

        @NotNull
        Long medico_id
) {

}
