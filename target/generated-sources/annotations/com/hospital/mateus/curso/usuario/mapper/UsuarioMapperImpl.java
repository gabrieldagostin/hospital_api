package com.hospital.mateus.curso.usuario.mapper;

import com.hospital.mateus.curso.usuario.dto.DadosCadastroUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosDetalhamentoUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosListagemUsuario;
import com.hospital.mateus.curso.usuario.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-02T11:26:15-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(DadosCadastroUsuario dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setLogin( dto.login() );
        usuario.setSenha( dto.senha() );

        return usuario;
    }

    @Override
    public DadosDetalhamentoUsuario toDetalhamentoDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        Long id = null;
        String login = null;
        String senha = null;

        id = usuario.getId();
        login = usuario.getLogin();
        senha = usuario.getSenha();

        DadosDetalhamentoUsuario dadosDetalhamentoUsuario = new DadosDetalhamentoUsuario( id, login, senha );

        return dadosDetalhamentoUsuario;
    }

    @Override
    public DadosListagemUsuario toListagemDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        Long id = null;
        String login = null;
        String senha = null;

        id = usuario.getId();
        login = usuario.getLogin();
        senha = usuario.getSenha();

        DadosListagemUsuario dadosListagemUsuario = new DadosListagemUsuario( id, login, senha );

        return dadosListagemUsuario;
    }
}
