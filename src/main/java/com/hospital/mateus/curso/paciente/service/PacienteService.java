package com.hospital.mateus.curso.paciente.service;

import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.medico.repository.MedicoRepository;
import com.hospital.mateus.curso.paciente.dto.*;
import com.hospital.mateus.curso.paciente.model.Paciente;
import com.hospital.mateus.curso.paciente.repository.PacienteRepository;
import com.hospital.mateus.curso.remedio.dto.DadosRemedio;
import com.hospital.mateus.curso.remedio.model.Remedio;
import com.hospital.mateus.curso.remedio.repository.RemedioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    private final RemedioRepository remedioRepository;

    private final MedicoRepository medicoRepository;

    public Paciente cadastrar(DadosCadastroPaciente dados) {
        Paciente paciente = new Paciente(dados);
        pacienteRepository.save(paciente);

        return paciente;
    }

    public List<DadosListagemPaciente> listar() {
        var lista = pacienteRepository.findAllByAtivoTrue().stream().map(DadosListagemPaciente::new).toList();

        return lista;
    }

    public Paciente detalhar(Long id) {
        return pacienteRepository.getReferenceById(id);
    }

    public Paciente atualizar(DadosAtualizarPaciente dados) {
        Paciente paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return paciente;
    }

    public void ativar(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.ativar();
    }

    public void desativar(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desativar();
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }

    public void associarRemedio(DadosAssociarRemedioPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.paciente_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Remedio remedio = remedioRepository.findById(dados.remedio_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.getRemedios().add(remedio);

        pacienteRepository.save(paciente);
    }

    public void removerRemedio(DadosAssociarRemedioPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.paciente_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Remedio remedio = remedioRepository.findById(dados.remedio_id())
                .orElseThrow(() -> new RuntimeException("Remédio não encontrado"));

        paciente.getRemedios().remove(remedio);

        pacienteRepository.save(paciente);
    }

    public List<DadosRemedio> listarRemedios(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        List<DadosRemedio> lista = paciente.getRemedios().stream()
                .map(remedio -> new DadosRemedio(remedio.getId(), remedio.getNome())).toList();

        return lista;
    }

    public void associarMedico(DadosAssociarMedicoPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.paciente_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(dados.medico_id())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        if (paciente.getMedico() == null) {
            paciente.setMedico(medico);
            pacienteRepository.save(paciente);
        }
    }

    public void removerMedico(DadosRemoverMedicoPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setMedico(null);
    }
}
