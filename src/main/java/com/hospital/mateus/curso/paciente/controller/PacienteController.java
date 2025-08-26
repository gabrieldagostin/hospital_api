package com.hospital.mateus.curso.paciente.controller;

import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.paciente.dto.*;
import com.hospital.mateus.curso.paciente.model.Paciente;
import com.hospital.mateus.curso.paciente.service.PacienteService;
import com.hospital.mateus.curso.remedio.dto.DadosRemedio;
import com.hospital.mateus.curso.remedio.model.Remedio;
import com.hospital.mateus.curso.medico.repository.MedicoRepository;
import com.hospital.mateus.curso.paciente.repository.PacienteRepository;
import com.hospital.mateus.curso.remedio.repository.RemedioRepository;
import jakarta.transaction.Transactional;
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

    private final PacienteRepository pacienteRepository;

    private final RemedioRepository remedioRepository;

    private final MedicoRepository medicoRepository;

    private final PacienteService pacienteService;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = pacienteService.cadastrar(dados);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new @Valid DadosDetalhamentoPaciente(paciente));
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemPaciente>> listar() {
        var lista = pacienteService.listar();

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable("id") long id) {
        Paciente paciente = pacienteService.detalhar(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizarPaciente dados) {
        Paciente paciente = pacienteService.atualizar(dados);

        return ResponseEntity.ok(new @Valid DadosDetalhamentoPaciente(paciente));
    }


    @PutMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable("id") long id) {
        pacienteService.ativar(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable("id") long id) {
        pacienteService.desativar(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        pacienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/associar-remedio")
    @Transactional
    public ResponseEntity<Void> associarRemedio(@RequestBody @Valid DadosAssociarRemedioPaciente dados) {
        pacienteService.associarRemedio(dados);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/remover-remedio")
    @Transactional
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
    @Transactional
    public ResponseEntity<Void> associarMedico(@RequestBody @Valid DadosAssociarMedicoPaciente dados) {
        pacienteService.associarMedico(dados);

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/remover-medico")
    @Transactional
    public ResponseEntity<Void> removerMedico(@RequestBody @Valid DadosRemoverMedicoPaciente dados) {
        pacienteService.removerMedico(dados);

        return ResponseEntity.noContent().build();
    }
}
