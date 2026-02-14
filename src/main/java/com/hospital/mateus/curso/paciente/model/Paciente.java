package com.hospital.mateus.curso.paciente.model;

import com.hospital.mateus.curso.core.model.Pessoa;
import com.hospital.mateus.curso.paciente.dto.DadosAtualizarPaciente;
import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.remedio.model.Remedio;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "paciente")
@Table(name = "pacientes")
public class Paciente extends Pessoa {

    @ManyToMany
    @JoinTable(
            name = "paciente_remedio",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "remedio_id")
    )
    private List<Remedio> remedios;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    public void atualizarInformacoes(DadosAtualizarPaciente dados) {
        if (dados.nome() != null && !dados.nome().isBlank() && !Objects.equals(this.nome, dados.nome())) {
            this.nome = dados.nome();
        }
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

}
