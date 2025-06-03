package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.enums.Especialidade;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotNull
        int idade,
        @NotBlank
        String cpf,
        @NotNull
        double salario,
        @NotBlank
        String crm,
        @Enumerated
        Especialidade especialidade

) {
}
