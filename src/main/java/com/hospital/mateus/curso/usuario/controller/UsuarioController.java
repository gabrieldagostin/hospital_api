package com.hospital.mateus.curso.usuario.controller;

import com.hospital.mateus.curso.usuario.dto.DadosAtualizarUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosCadastroUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosDetalhamentoUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosListagemUsuario;
import com.hospital.mateus.curso.usuario.service.UsuarioService;
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
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@Valid @RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder) {
        DadosDetalhamentoUsuario usuario = usuarioService.cadastrar(dados);

        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();

        return ResponseEntity.created(uri).body(usuario);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable("id") long id) {
        DadosDetalhamentoUsuario usuario = usuarioService.detalhar(id);

        return ResponseEntity.ok(usuario);
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> listar() {
        List<DadosListagemUsuario> lista = usuarioService.listar();

        return ResponseEntity.ok(lista);
    }


    @PutMapping
    public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@RequestBody @Valid DadosAtualizarUsuario dados) {
        DadosDetalhamentoUsuario usuario = usuarioService.atualizar(dados);

        return ResponseEntity.ok(usuario);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        usuarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
