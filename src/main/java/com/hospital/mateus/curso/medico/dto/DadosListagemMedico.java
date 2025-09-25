package com.hospital.mateus.curso.medico.dto;

import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.core.enums.Especialidade;
import com.hospital.mateus.curso.core.enums.Sexo;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record DadosListagemMedico(

        @NotNull
        Long id,

        String nome,

        Sexo sexo,

        Date dataNasc,

        String cpf,

        BigDecimal salario,

        String crm,

        Especialidade especialidade,

        boolean ativo) {

    public DadosListagemMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getSexo(),
                medico.getDataNasc(),
                medico.getCpf(),
                medico.getSalario(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.isAtivo()
        );
    }
}
