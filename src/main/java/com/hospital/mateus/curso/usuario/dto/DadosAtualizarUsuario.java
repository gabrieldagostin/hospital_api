package com.hospital.mateus.curso.usuario.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarUsuario(

        @NotNull
        Long id,

        String login,

        String senha
) {
}
