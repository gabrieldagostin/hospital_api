package com.hospital.mateus.curso.paciente.dto;

import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.paciente.model.Paciente;
import com.hospital.mateus.curso.core.enums.Sexo;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosDetalhamentoPaciente(

        @NotNull
        Long id,

        String nome,

        Sexo sexo,

        Date dataNasc,

        String cpf,

        Medico medico,

        boolean ativo
) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getSexo(),
                paciente.getDataNasc(),
                paciente.getCpf(),
                paciente.getMedico(),
                paciente.isAtivo());
    }
}
