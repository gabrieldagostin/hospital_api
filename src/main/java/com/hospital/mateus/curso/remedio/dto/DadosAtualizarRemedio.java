package com.hospital.mateus.curso.remedio.dto;

import com.hospital.mateus.curso.core.enums.Laboratorio;
import com.hospital.mateus.curso.core.enums.Via;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosAtualizarRemedio(

        @NotNull
        Long id,

        String nome,

        @PositiveOrZero
        Integer quantidade,

        Via via,

        Laboratorio laboratorio
) {

}
