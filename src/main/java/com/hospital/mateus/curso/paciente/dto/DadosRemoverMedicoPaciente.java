package com.hospital.mateus.curso.paciente.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosRemoverMedicoPaciente(

        @NotNull
        UUID pacienteId

) {}
