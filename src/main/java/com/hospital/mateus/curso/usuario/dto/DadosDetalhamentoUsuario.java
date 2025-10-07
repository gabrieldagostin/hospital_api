package com.hospital.mateus.curso.usuario.dto;

import com.hospital.mateus.curso.usuario.model.Usuario;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoUsuario(

        @NotNull
        Long id,

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
