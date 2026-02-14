package com.hospital.mateus.curso.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(

        @NotBlank
        String login,

        @NotBlank
        String senha

) {}
