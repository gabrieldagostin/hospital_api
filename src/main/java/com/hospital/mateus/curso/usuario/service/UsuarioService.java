package com.hospital.mateus.curso.usuario.service;

import com.hospital.mateus.curso.usuario.dto.DadosAtualizarUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosCadastroUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosDetalhamentoUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosListagemUsuario;
import com.hospital.mateus.curso.usuario.mapper.UsuarioMapper;
import com.hospital.mateus.curso.usuario.model.Usuario;
import com.hospital.mateus.curso.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;


    @Transactional
    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuario dados) {
        Usuario usuario = usuarioMapper.toEntity(dados);
        usuario.setLogin(usuario.getLogin().trim());
        usuarioRepository.save(usuario);

        return usuarioMapper.toDetalhamentoDto(usuario);
    }



    public DadosDetalhamentoUsuario detalhar(Long id) {
            Usuario usuario = usuarioRepository.getReferenceById(id);

            return usuarioMapper.toDetalhamentoDto(usuario);
    }


    public List<DadosListagemUsuario> listar() {
        return usuarioRepository.findAll().stream().map(
                usuarioMapper::toListagemDto).toList();
    }


    @Transactional
    public DadosDetalhamentoUsuario atualizar(DadosAtualizarUsuario dados) {
        Usuario usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);

        return usuarioMapper.toDetalhamentoDto(usuario);
    }


    @Transactional
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
