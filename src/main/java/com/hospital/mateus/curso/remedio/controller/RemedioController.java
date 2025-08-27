package com.hospital.mateus.curso.remedio.controller;

import com.hospital.mateus.curso.remedio.dto.DadosAtualizarRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosCadastroRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosDetalhamentoRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosListagemRemedio;
import com.hospital.mateus.curso.remedio.model.Remedio;
import com.hospital.mateus.curso.remedio.service.RemedioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/remedios")
public class RemedioController {

    private final RemedioService remedioService;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
        Remedio remedio = remedioService.cadastrar(dados);

        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemRemedio>> listar() {
        var lista = remedioService.listar();

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoRemedio> detalhar(@PathVariable("id") long id) {
        Remedio remedio = remedioService.detalhar(id);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
        Remedio remedio = remedioService.atualizarRemedio(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }


    @PutMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable("id") long id) {
        remedioService.ativar(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        remedioService.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable("id") long id) {
        remedioService.desativar(id);

        return ResponseEntity.noContent().build();
    }

}
