package com.hospital.mateus.curso.remedio.model;

import com.hospital.mateus.curso.remedio.dto.DadosAtualizarRemedio;
import com.hospital.mateus.curso.paciente.model.Paciente;
import com.hospital.mateus.curso.core.enums.Laboratorio;
import com.hospital.mateus.curso.core.enums.Via;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "remedios")
@Entity(name = "remedio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Remedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Via via;
    private String lote;
    private int quantidade;
    private LocalDate validade;

    @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;
    @ManyToMany(mappedBy = "remedios")
    private List<Paciente> pacientes = new ArrayList<>();
    private boolean ativo;

    public void atualizarInformacoes(DadosAtualizarRemedio dados) {
        if (dados.nome() != null && !dados.nome().isBlank() && !Objects.equals(this.nome, dados.nome())) {
            this.nome = dados.nome();
        }
        if (dados.via() != null && !Objects.equals(this.via, dados.via())) {
            this.via = dados.via();
        }
        if (dados.quantidade() != null && !Objects.equals(this.quantidade, dados.quantidade())) {
            this.quantidade = dados.quantidade();
        }
        if (dados.laboratorio() != null && !Objects.equals(this.laboratorio, dados.laboratorio())) {
            this.laboratorio = dados.laboratorio();
        }
    }

    public void desativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }
}
