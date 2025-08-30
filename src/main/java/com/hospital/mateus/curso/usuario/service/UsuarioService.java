package com.hospital.mateus.curso.usuario.service;

import com.hospital.mateus.curso.usuario.dto.DadosAtualizarUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosCadastroUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosDetalhamentoUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosListagemUsuario;
import com.hospital.mateus.curso.usuario.model.Usuario;
import com.hospital.mateus.curso.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;


    @Transactional
    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuario dados) {
        Usuario usuario = modelMapper.map(dados, Usuario.class);
        usuarioRepository.save(usuario);

        return modelMapper.map(usuario, DadosDetalhamentoUsuario.class);
    }


    public DadosDetalhamentoUsuario detalhar(Long id) {
            Usuario usuario = usuarioRepository.getReferenceById(id);

            return modelMapper.map(usuario, DadosDetalhamentoUsuario.class);
    }


    public List<DadosListagemUsuario> listar() {
        return usuarioRepository.findAll().stream().map(
                usuario -> modelMapper.map(usuario, DadosListagemUsuario.class)).toList();
    }


    @Transactional
    public DadosDetalhamentoUsuario atualizar(DadosAtualizarUsuario dados) {
        Usuario usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);

        return modelMapper.map(usuario, DadosDetalhamentoUsuario.class);
    }


    @Transactional
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
