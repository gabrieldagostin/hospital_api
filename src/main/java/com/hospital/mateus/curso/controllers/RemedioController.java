package com.hospital.mateus.curso.controllers;

import com.hospital.mateus.curso.dto.DadosAtualizarRemedio;
import com.hospital.mateus.curso.dto.DadosCadastroRemedio;
import com.hospital.mateus.curso.dto.DadosDetalhamentoRemedio;
import com.hospital.mateus.curso.dto.DadosListagemRemedio;
import com.hospital.mateus.curso.model.Remedio;
import com.hospital.mateus.curso.repository.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

    @Autowired
    private RemedioRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
        var remedio = new Remedio(dados);
        repository.save(remedio);

        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemRemedio>> listar() {
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoRemedio> detalhar(@PathVariable long id) {
        var remedio = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
        var remedio = repository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }


    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable long id) {
        var remedio = repository.getReferenceById(id);
        remedio.reativar();

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable long id) {
        var remedio = repository.getReferenceById(id);
        remedio.inativar();

        return ResponseEntity.noContent().build();
    }

}
