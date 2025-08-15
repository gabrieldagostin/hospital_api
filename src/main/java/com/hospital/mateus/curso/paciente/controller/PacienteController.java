package com.hospital.mateus.curso.paciente.controller;

import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.paciente.dto.*;
import com.hospital.mateus.curso.paciente.model.Paciente;
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


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dados);
        pacienteRepository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new @Valid DadosDetalhamentoPaciente(paciente));
    }


    @GetMapping
    public ResponseEntity<List<DadosListagemPaciente>> listar() {
        var lista = pacienteRepository.findAllByAtivoTrue().stream().map(DadosListagemPaciente::new).toList();

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizarPaciente dados) {
        Paciente paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);

        return ResponseEntity.ok(new @Valid DadosDetalhamentoPaciente(paciente));
    }


    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar(@PathVariable long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.reativar();

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        pacienteRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/associar-remedio")
    @Transactional
    public ResponseEntity<Void> associarRemedio(@RequestBody @Valid DadosAssociarRemedioPaciente dados) {
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
    public ResponseEntity<Void> removerRemedio(@RequestBody @Valid DadosAssociarRemedioPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.paciente_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Remedio remedio = remedioRepository.findById(dados.remedio_id())
                .orElseThrow(() -> new RuntimeException("Remédio não encontrado"));

        paciente.getRemedios().remove(remedio);

        pacienteRepository.save(paciente);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/remedios")
    public ResponseEntity<List<com.hospital.mateus.curso.remedio.dto.DadosRemedio>> listarRemedios(@PathVariable long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        List<DadosRemedio> lista = paciente.getRemedios().stream()
                .map(remedio -> new DadosRemedio(remedio.getId(), remedio.getNome())).toList();

        return ResponseEntity.ok(lista);
    }


    @PatchMapping("/associar-medico")
    @Transactional
    public ResponseEntity<Void> associarMedico(@RequestBody @Valid DadosAssociarMedicoPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.paciente_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(dados.medico_id())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        if (paciente.getMedico() == null) {
            paciente.setMedico(medico);
            pacienteRepository.save(paciente);
        }

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/remover-medico")
    @Transactional
    public ResponseEntity<Void> removerMedico(@RequestBody @Valid DadosRemoverMedicoPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.paciente_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setMedico(null);

        return ResponseEntity.noContent().build();
    }
}
