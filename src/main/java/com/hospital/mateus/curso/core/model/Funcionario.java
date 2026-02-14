package com.hospital.mateus.curso.core.model;

import com.hospital.mateus.curso.core.enums.Sexo;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Funcionario extends Pessoa {

    @Positive
    protected BigDecimal salario;

    public Funcionario(@NotBlank String nome, @NotBlank Sexo sexo, @NotNull Date dataNasc, @NotBlank String cpf, @NotNull BigDecimal salario) {
        super(nome, sexo, dataNasc, cpf);
        this.salario = salario;
    }
}
