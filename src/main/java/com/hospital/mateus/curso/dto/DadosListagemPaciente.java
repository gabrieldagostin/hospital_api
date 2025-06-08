package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.Paciente;
import com.hospital.mateus.curso.model.enums.Sexo;
import jakarta.validation.constraints.NotNull;

public record DadosListagemPaciente(

        @NotNull
        Long id,

        String nome,

        Sexo sexo,

        int idade,

        String cpf,

        boolean ativo
) {

    public DadosListagemPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getSexo(),
                paciente.getIdade(),
                paciente.getCpf(),
                paciente.isAtivo()
        );
    }
}
