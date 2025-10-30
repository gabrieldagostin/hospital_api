package com.hospital.mateus.curso.usuario.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosAtualizarUsuario(

        @NotNull
        UUID id,

        String login,

        String senha
) {}
