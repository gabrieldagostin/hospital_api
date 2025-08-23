package com.hospital.mateus.curso.usuario.dto;

import com.hospital.mateus.curso.usuario.model.Usuario;
import jakarta.validation.constraints.NotNull;

public record DadosListagemUsuario(

        @NotNull
        Long id,

        String login,

        String senha
) {

    public DadosListagemUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha()
        );
    }
}
