package com.hospital.mateus.curso.medico.service;

import com.hospital.mateus.curso.medico.dto.DadosAtualizarMedico;
import com.hospital.mateus.curso.medico.dto.DadosCadastroMedico;
import com.hospital.mateus.curso.medico.dto.DadosListagemMedico;
import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.medico.repository.MedicoRepository;
import com.hospital.mateus.curso.remedio.dto.DadosAtualizarRemedio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public Medico cadastrar(DadosCadastroMedico dados) {
        Medico medico = new Medico(dados);
        medicoRepository.save(medico);

        return medico;
    }

    public List<DadosListagemMedico> listar() {
        return medicoRepository.findAllByAtivoTrue().stream().map(DadosListagemMedico::new).toList();
    }

    public Medico detalhar(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);

        return medico;
    }

    public Medico atualizar(DadosAtualizarMedico dados) {
        Medico medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return medico;
    }

    public void ativar(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.ativar();
    }

    public void deletar(Long id) {
        medicoRepository.deleteById(id);
    }

    public void desativar(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desativar();
    }
}
