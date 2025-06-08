package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.enums.Laboratorio;
import com.hospital.mateus.curso.model.enums.Via;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(

        @NotNull
        Long id,

        String nome,

        Via via,

        Laboratorio laboratorio
) {

}
