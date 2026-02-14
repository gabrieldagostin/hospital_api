package com.hospital.mateus.curso.paciente.dto;

import com.hospital.mateus.curso.paciente.model.Paciente;
import com.hospital.mateus.curso.core.enums.Sexo;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record DadosListagemPaciente(

        @NotNull
        UUID id,

        String nome,

        Sexo sexo,

        Date dataNasc,

        String cpf,

        boolean ativo
) {

    public DadosListagemPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getSexo(),
                paciente.getDataNasc(),
                paciente.getCpf(),
                paciente.isAtivo()
        );
    }
}
