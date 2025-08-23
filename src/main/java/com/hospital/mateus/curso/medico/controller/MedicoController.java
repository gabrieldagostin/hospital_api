package com.hospital.mateus.curso.medico.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.hospital.mateus.curso.medico.dto.DadosAtualizarMedico;
import com.hospital.mateus.curso.medico.dto.DadosCadastroMedico;
import com.hospital.mateus.curso.medico.dto.DadosDetalhamentoMedico;
import com.hospital.mateus.curso.medico.dto.DadosListagemMedico;
import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.medico.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        Medico medico = new Medico(dados);
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
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable("id") long id) {
        Medico medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar(@PathVariable("id") long id) {
        Medico medico = repository.getReferenceById(id);
        medico.reativar();

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable("id") long id) {
        Medico medico = repository.getReferenceById(id);
        medico.inativar();

        return ResponseEntity.noContent().build();
    }

}
