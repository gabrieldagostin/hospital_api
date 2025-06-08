package com.hospital.mateus.curso.dto;

import com.hospital.mateus.curso.model.Medico;
import com.hospital.mateus.curso.model.enums.Especialidade;
import com.hospital.mateus.curso.model.enums.Sexo;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoMedico(

        @NotNull
        Long id,

        String nome,

        Sexo sexo,

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
                medico.getSexo(),
                medico.getIdade(),
                medico.getCpf(),
                medico.getSalario(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.isAtivo());
    }
}
