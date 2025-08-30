package com.hospital.mateus.curso.paciente.controller;

import com.hospital.mateus.curso.paciente.dto.*;
import com.hospital.mateus.curso.paciente.service.PacienteService;
import com.hospital.mateus.curso.remedio.dto.DadosRemedio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacientes")
public class PacienteController {


    private final PacienteService pacienteService;


    @PostMapping
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoPaciente paciente = pacienteService.cadastrar(dados);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.id()).toUri();

        return ResponseEntity.created(uri).body(paciente);
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemPaciente>> listar() {
        List<DadosListagemPaciente> lista = pacienteService.listar();

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable("id") long id) {
        DadosDetalhamentoPaciente paciente = pacienteService.detalhar(id);

        return ResponseEntity.ok(paciente);
    }


    @PutMapping
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizarPaciente dados) {
        DadosDetalhamentoPaciente paciente = pacienteService.atualizar(dados);

        return ResponseEntity.ok(paciente);
    }


    @PutMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable("id") long id) {
        pacienteService.ativar(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable("id") long id) {
        pacienteService.desativar(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        pacienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/associar-remedio")
    public ResponseEntity<Void> associarRemedio(@RequestBody @Valid DadosAssociarRemedioPaciente dados) {
        pacienteService.associarRemedio(dados);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/remover-remedio")
    public ResponseEntity<Void> removerRemedio(@RequestBody @Valid DadosAssociarRemedioPaciente dados) {
        pacienteService.removerRemedio(dados);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/remedios")
    public ResponseEntity<List<DadosRemedio>> listarRemedios(@PathVariable("id") long id) {
        List<DadosRemedio> lista = pacienteService.listarRemedios(id);

        return ResponseEntity.ok(lista);
    }


    @PatchMapping("/associar-medico")
    public ResponseEntity<Void> associarMedico(@RequestBody @Valid DadosAssociarMedicoPaciente dados) {
        pacienteService.associarMedico(dados);

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/remover-medico")
    public ResponseEntity<Void> removerMedico(@RequestBody @Valid DadosRemoverMedicoPaciente dados) {
        pacienteService.removerMedico(dados);

        return ResponseEntity.noContent().build();
    }
}
