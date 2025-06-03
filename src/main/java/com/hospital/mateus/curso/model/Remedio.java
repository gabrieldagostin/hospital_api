package com.hospital.mateus.curso.model;

import com.hospital.mateus.curso.dto.DadosAtualizarRemedio;
import com.hospital.mateus.curso.dto.DadosCadastroRemedio;
import com.hospital.mateus.curso.model.enums.Laboratorio;
import com.hospital.mateus.curso.model.enums.Via;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public Remedio(DadosCadastroRemedio dados) {
        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.validade = dados.validade();
        this.laboratorio = dados.laboratorio();
        this.ativo = true;
    }

    public void atualizarInformacoes(@Valid DadosAtualizarRemedio dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.via() != null) {
            this.via = dados.via();
        }
        if (dados.laboratorio() != null) {
            this.laboratorio = dados.laboratorio();
        }
    }

    public void inativar() {
        this.ativo = false;
    }

    public void reativar() {
        this.ativo = true;
    }
}
