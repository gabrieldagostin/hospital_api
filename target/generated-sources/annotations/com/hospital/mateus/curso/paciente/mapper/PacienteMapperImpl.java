package com.hospital.mateus.curso.paciente.mapper;

import com.hospital.mateus.curso.core.enums.Sexo;
import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.paciente.dto.DadosCadastroPaciente;
import com.hospital.mateus.curso.paciente.dto.DadosDetalhamentoPaciente;
import com.hospital.mateus.curso.paciente.dto.DadosListagemPaciente;
import com.hospital.mateus.curso.paciente.model.Paciente;
import com.hospital.mateus.curso.remedio.dto.DadosRemedio;
import com.hospital.mateus.curso.remedio.model.Remedio;
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
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public Paciente toEntity(DadosCadastroPaciente dto) {
        if ( dto == null ) {
            return null;
        }

        Paciente paciente = new Paciente();

        paciente.setNome( dto.nome() );
        paciente.setSexo( dto.sexo() );
        paciente.setDataNasc( dto.dataNasc() );
        paciente.setCpf( dto.cpf() );

        return paciente;
    }

    @Override
    public DadosDetalhamentoPaciente toDetalhamentoDto(Paciente paciente) {
        if ( paciente == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        Sexo sexo = null;
        Date dataNasc = null;
        String cpf = null;
        Medico medico = null;
        boolean ativo = false;

        id = paciente.getId();
        nome = paciente.getNome();
        sexo = paciente.getSexo();
        dataNasc = paciente.getDataNasc();
        cpf = paciente.getCpf();
        medico = paciente.getMedico();
        ativo = paciente.isAtivo();

        DadosDetalhamentoPaciente dadosDetalhamentoPaciente = new DadosDetalhamentoPaciente( id, nome, sexo, dataNasc, cpf, medico, ativo );

        return dadosDetalhamentoPaciente;
    }

    @Override
    public DadosListagemPaciente toListagemDto(Paciente paciente) {
        if ( paciente == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        Sexo sexo = null;
        Date dataNasc = null;
        String cpf = null;
        boolean ativo = false;

        id = paciente.getId();
        nome = paciente.getNome();
        sexo = paciente.getSexo();
        dataNasc = paciente.getDataNasc();
        cpf = paciente.getCpf();
        ativo = paciente.isAtivo();

        DadosListagemPaciente dadosListagemPaciente = new DadosListagemPaciente( id, nome, sexo, dataNasc, cpf, ativo );

        return dadosListagemPaciente;
    }

    @Override
    public DadosRemedio toDadosRemedioDto(Remedio remedio) {
        if ( remedio == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;

        id = remedio.getId();
        nome = remedio.getNome();

        DadosRemedio dadosRemedio = new DadosRemedio( id, nome );

        return dadosRemedio;
    }
}
