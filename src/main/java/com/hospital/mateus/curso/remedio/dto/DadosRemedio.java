package com.hospital.mateus.curso.remedio.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosRemedio(

        @NotNull
        UUID id,

        String nome
) {}
