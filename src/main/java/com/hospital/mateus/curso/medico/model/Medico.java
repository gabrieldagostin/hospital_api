package com.hospital.mateus.curso.medico.model;

import com.hospital.mateus.curso.core.model.Funcionario;
import com.hospital.mateus.curso.core.enums.Especialidade;
import com.hospital.mateus.curso.medico.dto.DadosAtualizarMedico;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "medicos")
@Entity(name = "medico")
public class Medico extends Funcionario {

    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    public void atualizarInformacoes(DadosAtualizarMedico dados) {
        if (dados.nome() != null && !Objects.equals(this.nome, dados.nome())) {
            setNome(dados.nome());
        }
        if (dados.salario() != null && !Objects.equals(this.salario, dados.salario())) {
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
