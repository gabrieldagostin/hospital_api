package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.enums.Laboratorio;
import com.hospital.mateus.curso.model.enums.Via;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroRemedio(

        @NotBlank
        String nome,
        @Enumerated
        Via via,
        @NotBlank
        String lote,
        @NotNull
        int quantidade,
        @Future
        LocalDate validade,
        @Enumerated
        Laboratorio laboratorio) {

}
