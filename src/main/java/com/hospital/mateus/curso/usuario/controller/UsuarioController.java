package com.hospital.mateus.curso.usuario.controller;

import com.hospital.mateus.curso.usuario.dto.DadosAtualizarUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosCadastroUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosDetalhamentoUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosListagemUsuario;
import com.hospital.mateus.curso.usuario.model.Usuario;
import com.hospital.mateus.curso.usuario.service.UsuarioService;
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

    private final UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@Valid @RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioService.cadastrar(dados);

        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new @Valid DadosDetalhamentoUsuario(usuario));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable("id") long id) {
        Usuario usuario = usuarioService.detalhar(id);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> listar() {
        List<DadosListagemUsuario> lista = usuarioService.listar();

        return ResponseEntity.ok(lista);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@RequestBody @Valid DadosAtualizarUsuario dados) {
        Usuario usuario = usuarioService.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        usuarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
