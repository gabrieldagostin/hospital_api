package com.hospital.mateus.curso.medico.dto;

import com.hospital.mateus.curso.core.enums.Especialidade;
import com.hospital.mateus.curso.core.enums.Sexo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.Date;

public record DadosCadastroMedico(

        @NotBlank
        String nome,

        @Enumerated(EnumType.STRING)
        Sexo sexo,

        @NotNull
        @Past
        Date dataNasc,

        @NotBlank
        @CPF
        String cpf,

        @NotNull
        @Positive
        BigDecimal salario,

        @NotBlank
        String crm,

        @Enumerated(EnumType.STRING)
        Especialidade especialidade
) {}
