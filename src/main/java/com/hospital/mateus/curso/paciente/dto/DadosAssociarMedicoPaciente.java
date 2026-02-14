package com.hospital.mateus.curso.paciente.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosAssociarMedicoPaciente(

        @NotNull
        UUID paciente_id,

        @NotNull
        UUID medico_id
) {}
