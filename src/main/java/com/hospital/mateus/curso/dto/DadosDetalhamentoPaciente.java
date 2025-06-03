package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.Paciente;

public record DadosDetalhamentoPaciente(
        long id,
        String nome,
        int idade,
        String cpf,
        boolean ativo
) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getIdade(),
                paciente.getCpf(),
                paciente.isAtivo());
    }

}
