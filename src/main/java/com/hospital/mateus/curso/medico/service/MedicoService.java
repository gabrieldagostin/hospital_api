package com.hospital.mateus.curso.medico.service;

import com.hospital.mateus.curso.medico.dto.DadosAtualizarMedico;
import com.hospital.mateus.curso.medico.dto.DadosCadastroMedico;
import com.hospital.mateus.curso.medico.dto.DadosDetalhamentoMedico;
import com.hospital.mateus.curso.medico.dto.DadosListagemMedico;
import com.hospital.mateus.curso.medico.mapper.MedicoMapper;
import com.hospital.mateus.curso.medico.model.Medico;
import com.hospital.mateus.curso.medico.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicoService {


    private final MedicoRepository medicoRepository;

    private final MedicoMapper medicoMapper;


    @Transactional
    public DadosDetalhamentoMedico cadastrar(DadosCadastroMedico dados) {
        Medico medico = medicoMapper.toEntity(dados);
        medico.ativar();
        medico.setNome(medico.getNome().trim());
        medico.setCrm(medico.getCrm().trim());
        medicoRepository.save(medico);

        return medicoMapper.toDetalhamentoDto(medico);
    }


    public List<DadosListagemMedico> listar() {
        return medicoRepository.findAllByAtivoTrue().stream().map(
                medicoMapper::toListagemDto).toList();
    }


    public DadosDetalhamentoMedico detalhar(UUID id) {
        Medico medico = medicoRepository.getReferenceById(id);

        return medicoMapper.toDetalhamentoDto(medico);
    }


    @Transactional
    public DadosDetalhamentoMedico atualizar(DadosAtualizarMedico dados) {
        Medico medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return medicoMapper.toDetalhamentoDto(medico);
    }


    @Transactional
    public void ativar(UUID id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.ativar();
    }


    @Transactional
    public void deletar(UUID id) {
        medicoRepository.deleteById(id);
    }


    @Transactional
    public void desativar(UUID id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desativar();
    }
}
