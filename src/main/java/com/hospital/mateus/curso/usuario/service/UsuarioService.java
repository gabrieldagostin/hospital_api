package com.hospital.mateus.curso.usuario.service;

import com.hospital.mateus.curso.usuario.dto.DadosAtualizarUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosCadastroUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosListagemUsuario;
import com.hospital.mateus.curso.usuario.model.Usuario;
import com.hospital.mateus.curso.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario cadastrar(DadosCadastroUsuario dados) {
        Usuario usuario = new Usuario(dados);
        usuarioRepository.save(usuario);

        return usuario;
    }

    public Usuario detalhar(Long id) {
        return usuarioRepository.getReferenceById(id);
    }

    public List<DadosListagemUsuario> listar() {
        List<DadosListagemUsuario> lista = usuarioRepository.findAll().stream().map(DadosListagemUsuario::new).toList();

        return lista;
    }

    public Usuario atualizar(DadosAtualizarUsuario dados) {
        Usuario usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);

        return usuario;
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
