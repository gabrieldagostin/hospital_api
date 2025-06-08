package com.hospital.mateus.curso.model;

import com.hospital.mateus.curso.model.enums.Sexo;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Funcionario extends Pessoa{

    @Positive
    protected double salario;

    public Funcionario(@NotBlank String nome, @NotBlank Sexo sexo, @NotNull int idade, @NotBlank String cpf, @NotNull double salario) {
        super(nome, sexo, idade, cpf);
        this.salario = salario;
    }
}
