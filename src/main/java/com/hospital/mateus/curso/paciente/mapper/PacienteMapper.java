package com.hospital.mateus.curso.paciente.mapper;

import com.hospital.mateus.curso.paciente.dto.DadosCadastroPaciente;
import com.hospital.mateus.curso.paciente.dto.DadosDetalhamentoPaciente;
import com.hospital.mateus.curso.paciente.dto.DadosListagemPaciente;
import com.hospital.mateus.curso.paciente.model.Paciente;
import com.hospital.mateus.curso.remedio.dto.DadosRemedio;
import com.hospital.mateus.curso.remedio.model.Remedio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    Paciente toEntity(DadosCadastroPaciente dto);
    DadosDetalhamentoPaciente toDetalhamentoDto(Paciente paciente);
    DadosListagemPaciente toListagemDto(Paciente paciente);
    DadosRemedio toDadosRemedioDto(Remedio remedio);
}
