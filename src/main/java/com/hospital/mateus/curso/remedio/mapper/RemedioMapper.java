package com.hospital.mateus.curso.remedio.mapper;

import com.hospital.mateus.curso.remedio.dto.DadosCadastroRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosDetalhamentoRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosListagemRemedio;
import com.hospital.mateus.curso.remedio.model.Remedio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RemedioMapper {

    Remedio toEntity(DadosCadastroRemedio dto);
    DadosDetalhamentoRemedio toDetalhamentoDto(Remedio remedio);
    DadosListagemRemedio toListagemDto(Remedio remedio);
}
