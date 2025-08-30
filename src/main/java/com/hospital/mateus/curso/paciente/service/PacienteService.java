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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PacienteService {


    private final PacienteRepository pacienteRepository;

    private final RemedioRepository remedioRepository;

    private final MedicoRepository medicoRepository;

    private final ModelMapper modelMapper;


    @Transactional
    public DadosDetalhamentoPaciente cadastrar(DadosCadastroPaciente dados) {
        Paciente paciente = modelMapper.map(dados, Paciente.class);
        pacienteRepository.save(paciente);

        return modelMapper.map(paciente, DadosDetalhamentoPaciente.class);
    }


    public List<DadosListagemPaciente> listar() {
        return pacienteRepository.findAllByAtivoTrue().stream().map(
                paciente -> modelMapper.map(paciente, DadosListagemPaciente.class)).toList();
    }


    public DadosDetalhamentoPaciente detalhar(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        return modelMapper.map(paciente, DadosDetalhamentoPaciente.class);
    }


    @Transactional
    public DadosDetalhamentoPaciente atualizar(DadosAtualizarPaciente dados) {
        Paciente paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return modelMapper.map(paciente, DadosDetalhamentoPaciente.class);
    }


    @Transactional
    public void ativar(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.ativar();
    }


    @Transactional
    public void desativar(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desativar();
    }


    @Transactional
    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }


    @Transactional
    public void associarRemedio(DadosAssociarRemedioPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.paciente_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Remedio remedio = remedioRepository.findById(dados.remedio_id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.getRemedios().add(remedio);

        pacienteRepository.save(paciente);
    }


    @Transactional
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
                .map(remedio -> modelMapper.map(remedio, DadosRemedio.class)).toList();

        return lista;
    }


    @Transactional
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


    @Transactional
    public void removerMedico(DadosRemoverMedicoPaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setMedico(null);
    }
}
