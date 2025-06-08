package com.hospital.mateus.curso.model;

import com.hospital.mateus.curso.dto.DadosAtualizarMedico;
import com.hospital.mateus.curso.dto.DadosCadastroMedico;
import com.hospital.mateus.curso.model.enums.Especialidade;
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

    public Medico(DadosCadastroMedico dados) {
        super(
                dados.nome(),
                dados.sexo(),
                dados.idade(),
                dados.cpf(),
                dados.salario());
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();

    }

    public void atualizarInformacoes(@Valid DadosAtualizarMedico dados) {
        if (dados.nome() != null) {
            setNome(dados.nome());
        }
        if (dados.salario() != null) {
            setSalario(dados.salario());
        }
    }

    public void reativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

}
