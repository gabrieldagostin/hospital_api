package com.hospital.mateus.curso.medico.mapper;

import com.hospital.mateus.curso.medico.dto.DadosCadastroMedico;
import com.hospital.mateus.curso.medico.dto.DadosDetalhamentoMedico;
import com.hospital.mateus.curso.medico.dto.DadosListagemMedico;
import com.hospital.mateus.curso.medico.model.Medico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    Medico toEntity(DadosCadastroMedico dto);
    DadosDetalhamentoMedico toDetalhamentoDto(Medico medico);
    DadosListagemMedico toListagemDto(Medico medico);
}
