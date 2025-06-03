package com.hospital.mateus.curso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPaciente(
        @NotBlank
        String nome,
        @NotNull
        int idade,
        @NotBlank
        String cpf) {

}
