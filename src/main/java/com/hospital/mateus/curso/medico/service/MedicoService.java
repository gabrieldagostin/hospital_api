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

@Service
@RequiredArgsConstructor
public class MedicoService {


    private final MedicoRepository medicoRepository;

    private final MedicoMapper medicoMapper;


    @Transactional
    public DadosDetalhamentoMedico cadastrar(DadosCadastroMedico dados) {
        Medico medico = medicoMapper.toEntity(dados);
        medicoRepository.save(medico);

        return medicoMapper.toDetalhamentoDto(medico);
    }


    public List<DadosListagemMedico> listar() {
        return medicoRepository.findAllByAtivoTrue().stream().map(
                medico -> medicoMapper.toListagemDto(medico)).toList();
    }


    public DadosDetalhamentoMedico detalhar(Long id) {
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
    public void ativar(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.ativar();
    }


    @Transactional
    public void deletar(Long id) {
        medicoRepository.deleteById(id);
    }


    @Transactional
    public void desativar(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desativar();
    }
}
