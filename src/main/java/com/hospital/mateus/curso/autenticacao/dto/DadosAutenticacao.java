package com.hospital.mateus.curso.autenticacao.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(

        @NotBlank
        String login,
        @NotBlank
        String senha
) {}
