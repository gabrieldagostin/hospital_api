package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.Medico;
import com.hospital.mateus.curso.model.Paciente;
import com.hospital.mateus.curso.model.enums.Sexo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoPaciente(

        @NotNull
        Long id,

        String nome,

        Sexo sexo,

        int idade,

        String cpf,

        Medico medico,

        boolean ativo
) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getSexo(),
                paciente.getIdade(),
                paciente.getCpf(),
                paciente.getMedico(),
                paciente.isAtivo());
    }

}
