package com.hospital.mateus.curso.medico.model;

import com.hospital.mateus.curso.core.model.Funcionario;
import com.hospital.mateus.curso.core.enums.Especialidade;
import com.hospital.mateus.curso.medico.dto.DadosAtualizarMedico;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "medicos")
@Entity(name = "medico")
public class Medico extends Funcionario {

    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    public void atualizarInformacoes(@Valid DadosAtualizarMedico dados) {
        if (dados.nome() != null) {
            setNome(dados.nome());
        }
        if (dados.salario() != null) {
            setSalario(dados.salario());
        }
    }

    public void ativar() {
        setAtivo(true);
    }

    public void desativar() {
        setAtivo(false);
    }

}
