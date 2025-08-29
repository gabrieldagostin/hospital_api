package com.hospital.mateus.curso.medico.controller;

import java.util.List;

import com.hospital.mateus.curso.medico.service.MedicoService;
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
import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoMedico medico = medicoService.cadastrar(dados);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.id()).toUri();

        return ResponseEntity.created(uri).body(medico);
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemMedico>> listar() {
        List<DadosListagemMedico> lista = medicoService.listar();

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable("id") long id) {
        DadosDetalhamentoMedico medico = medicoService.detalhar(id);

        return ResponseEntity.ok(medico);
    }


    @PutMapping
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        DadosDetalhamentoMedico medico = medicoService.atualizar(dados);

        return ResponseEntity.ok(medico);
    }


    @PutMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable("id") long id) {
        medicoService.ativar(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        medicoService.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable("id") long id) {
        medicoService.desativar(id);

        return ResponseEntity.noContent().build();
    }

}
