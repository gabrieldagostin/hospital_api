package com.hospital.mateus.curso.usuario.dto;

import com.hospital.mateus.curso.usuario.model.Usuario;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosDetalhamentoUsuario(

        @NotNull
        UUID id,

        String login,

        String senha
) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha()
        );
    }
}
