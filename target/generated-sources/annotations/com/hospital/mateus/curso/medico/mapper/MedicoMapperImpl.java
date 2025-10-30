package com.hospital.mateus.curso.medico.mapper;

import com.hospital.mateus.curso.core.enums.Especialidade;
import com.hospital.mateus.curso.core.enums.Sexo;
import com.hospital.mateus.curso.medico.dto.DadosCadastroMedico;
import com.hospital.mateus.curso.medico.dto.DadosDetalhamentoMedico;
import com.hospital.mateus.curso.medico.dto.DadosListagemMedico;
import com.hospital.mateus.curso.medico.model.Medico;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T08:58:50-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class MedicoMapperImpl implements MedicoMapper {

    @Override
    public Medico toEntity(DadosCadastroMedico dto) {
        if ( dto == null ) {
            return null;
        }

        Medico medico = new Medico();

        medico.setNome( dto.nome() );
        medico.setSexo( dto.sexo() );
        medico.setDataNasc( dto.dataNasc() );
        medico.setCpf( dto.cpf() );
        medico.setSalario( dto.salario() );
        medico.setCrm( dto.crm() );
        medico.setEspecialidade( dto.especialidade() );

        return medico;
    }

    @Override
    public DadosDetalhamentoMedico toDetalhamentoDto(Medico medico) {
        if ( medico == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        Sexo sexo = null;
        Date dataNasc = null;
        String cpf = null;
        BigDecimal salario = null;
        String crm = null;
        Especialidade especialidade = null;
        boolean ativo = false;

        id = medico.getId();
        nome = medico.getNome();
        sexo = medico.getSexo();
        dataNasc = medico.getDataNasc();
        cpf = medico.getCpf();
        salario = medico.getSalario();
        crm = medico.getCrm();
        especialidade = medico.getEspecialidade();
        ativo = medico.isAtivo();

        DadosDetalhamentoMedico dadosDetalhamentoMedico = new DadosDetalhamentoMedico( id, nome, sexo, dataNasc, cpf, salario, crm, especialidade, ativo );

        return dadosDetalhamentoMedico;
    }

    @Override
    public DadosListagemMedico toListagemDto(Medico medico) {
        if ( medico == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        Sexo sexo = null;
        Date dataNasc = null;
        String cpf = null;
        BigDecimal salario = null;
        String crm = null;
        Especialidade especialidade = null;
        boolean ativo = false;

        id = medico.getId();
        nome = medico.getNome();
        sexo = medico.getSexo();
        dataNasc = medico.getDataNasc();
        cpf = medico.getCpf();
        salario = medico.getSalario();
        crm = medico.getCrm();
        especialidade = medico.getEspecialidade();
        ativo = medico.isAtivo();

        DadosListagemMedico dadosListagemMedico = new DadosListagemMedico( id, nome, sexo, dataNasc, cpf, salario, crm, especialidade, ativo );

        return dadosListagemMedico;
    }
}
