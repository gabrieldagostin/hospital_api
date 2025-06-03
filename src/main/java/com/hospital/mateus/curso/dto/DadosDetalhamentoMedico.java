package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.Medico;
import com.hospital.mateus.curso.model.enums.Especialidade;

public record DadosDetalhamentoMedico(

        long id,

        String nome,

        int idade,

        String cpf,

        double salario,

        String crm,

        Especialidade especialidade,

        boolean ativo
) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getIdade(),
                medico.getCpf(),
                medico.getSalario(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.isAtivo());
    }
}
