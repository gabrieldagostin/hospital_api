package com.hospital.mateus.curso.core.model;

import com.hospital.mateus.curso.core.enums.Sexo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String nome;
    @Enumerated(EnumType.STRING)
    protected Sexo sexo;
    protected Date dataNasc;
    protected String cpf;
    protected boolean ativo;

    public Pessoa(@NotBlank String nome, @NotBlank Sexo sexo, @NotNull Date dataNasc, @NotBlank String cpf) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.ativo = true;
    }
}
