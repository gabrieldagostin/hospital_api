package com.hospital.mateus.curso.usuario.controller;

import com.hospital.mateus.curso.usuario.dto.DadosAtualizarUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosCadastroUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosDetalhamentoUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosListagemUsuario;
import com.hospital.mateus.curso.usuario.model.Usuario;
import com.hospital.mateus.curso.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@Valid @RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = new Usuario(dados);

        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new @Valid DadosDetalhamentoUsuario(usuario));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalharUsuario(@PathVariable("id") long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> listarPacientes() {
        List<DadosListagemUsuario> lista = usuarioRepository.findAll().stream().map(DadosListagemUsuario::new).toList();

        return ResponseEntity.ok(lista);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> alterarUsuario(@RequestBody @Valid DadosAtualizarUsuario dados) {
        Usuario usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizarUsuario(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") long id) {
        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
