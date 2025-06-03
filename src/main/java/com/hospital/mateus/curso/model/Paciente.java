package com.hospital.mateus.curso.model;

import com.hospital.mateus.curso.dto.DadosAssociarRemedioPaciente;
import com.hospital.mateus.curso.dto.DadosAtualizarPaciente;
import com.hospital.mateus.curso.dto.DadosCadastroPaciente;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente extends Pessoa {

    @ManyToMany
    @JoinTable(
            name = "paciente_remedio",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "remedio_id")
    )
    private List<Remedio> remedios;
    

    public Paciente(DadosCadastroPaciente dados) {
        super(
                dados.nome(),
                dados.idade(),
                dados.cpf()
        );
    }

    public void atualizarPaciente(@Valid DadosAtualizarPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
    }

    public void reativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

}
