package com.hospital.mateus.curso.controllers;

import com.hospital.mateus.curso.dto.*;
import com.hospital.mateus.curso.model.Paciente;
import com.hospital.mateus.curso.model.Remedio;
import com.hospital.mateus.curso.repository.PacienteRepository;
import com.hospital.mateus.curso.repository.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private RemedioRepository remedioRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        pacienteRepository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemPaciente>> listar() {
        var lista = pacienteRepository.findAllByAtivoTrue().stream().map(DadosListagemPaciente::new).toList();

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable long id) {
        var paciente = pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizarPaciente dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }


    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar(@PathVariable long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.reativar();

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        pacienteRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/adicionar-remedio")
    @Transactional
    public ResponseEntity<Void> adicionarRemedio(@RequestBody DadosAssociarRemedioPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.paciente_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Remedio remedio = remedioRepository.findById(dados.remedio_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.getRemedios().add(remedio);

        pacienteRepository.save(paciente);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/remover-remedio")
    @Transactional
    public ResponseEntity<Void> removerRemedio(@RequestBody DadosAssociarRemedioPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.paciente_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Remedio remedio = remedioRepository.findById(dados.remedio_id())
                .orElseThrow(() -> new RuntimeException("Remédio não encontrado"));

        paciente.getRemedios().remove(remedio);

        pacienteRepository.save(paciente);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/remedios")
    public ResponseEntity<List<DadosRemedio>> listarRemedios(@PathVariable long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        List<DadosRemedio> lista = paciente.getRemedios().stream()
                .map(remedio -> new DadosRemedio(remedio.getId(), remedio.getNome())).toList();

        return ResponseEntity.ok(lista);
    }



}
