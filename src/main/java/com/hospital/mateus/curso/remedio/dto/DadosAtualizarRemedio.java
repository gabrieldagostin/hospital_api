package com.hospital.mateus.curso.remedio.dto;

import com.hospital.mateus.curso.core.enums.Laboratorio;
import com.hospital.mateus.curso.core.enums.Via;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(

        @NotNull
        Long id,

        String nome,

        Via via,

        Laboratorio laboratorio
) {

}
