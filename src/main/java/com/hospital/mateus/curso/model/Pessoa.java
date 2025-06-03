package com.hospital.mateus.curso.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    protected int idade;
    protected String cpf;
    protected boolean ativo;

    public Pessoa(@NotBlank String nome, @NotNull int idade, @NotBlank String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.ativo = true;
    }
}
