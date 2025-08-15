package com.hospital.mateus.curso.remedio.dto;

import com.hospital.mateus.curso.core.enums.Laboratorio;
import com.hospital.mateus.curso.core.enums.Via;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroRemedio(

        @NotBlank
        String nome,

        @Enumerated(EnumType.STRING)
        Via via,

        @NotBlank
        String lote,

        @NotNull
        int quantidade,

        @Future
        LocalDate validade,

        @Enumerated(EnumType.STRING)
        Laboratorio laboratorio
) {

}
