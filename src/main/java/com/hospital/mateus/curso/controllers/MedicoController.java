package com.hospital.mateus.curso.controllers;

import com.hospital.mateus.curso.dto.*;
import com.hospital.mateus.curso.model.Medico;
import com.hospital.mateus.curso.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemMedico>> listar() {
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemMedico::new).toList();

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable long id) {
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar(@PathVariable long id) {
        var medico = repository.getReferenceById(id);
        medico.reativar();

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable long id) {
        var medico = repository.getReferenceById(id);
        medico.inativar();

        return ResponseEntity.noContent().build();
    }

}
