package com.hospital.mateus.curso.usuario.mapper;

import com.hospital.mateus.curso.usuario.dto.DadosCadastroUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosDetalhamentoUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosListagemUsuario;
import com.hospital.mateus.curso.usuario.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(DadosCadastroUsuario dto);

    DadosDetalhamentoUsuario toDetalhamentoDto(Usuario usuario);

    DadosListagemUsuario toListagemDto(Usuario usuario);


}
