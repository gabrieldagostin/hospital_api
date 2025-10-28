package com.hospital.mateus.curso.remedio.service;

import com.hospital.mateus.curso.remedio.dto.DadosAtualizarRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosCadastroRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosDetalhamentoRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosListagemRemedio;
import com.hospital.mateus.curso.remedio.mapper.RemedioMapper;
import com.hospital.mateus.curso.remedio.model.Remedio;
import com.hospital.mateus.curso.remedio.repository.RemedioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RemedioService {


    private final RemedioRepository remedioRepository;

    private final RemedioMapper remedioMapper;


    @Transactional
    public DadosDetalhamentoRemedio cadastrar(DadosCadastroRemedio dados) {
        Remedio remedio = remedioMapper.toEntity(dados);
        remedio.ativar();
        remedio.setNome(remedio.getNome().trim());
        remedio.setLote(remedio.getLote().trim());
        remedioRepository.save(remedio);

        return remedioMapper.toDetalhamentoDto(remedio);
    }


    public List<DadosListagemRemedio> listar() {
        return remedioRepository.findAllByAtivoTrue().stream().map(
                remedioMapper::toListagemDto).toList();
    }


    public DadosDetalhamentoRemedio detalhar(Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);

        return remedioMapper.toDetalhamentoDto(remedio);
    }


    @Transactional
    public DadosDetalhamentoRemedio atualizarRemedio(DadosAtualizarRemedio dados) {
        Remedio remedio = remedioRepository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);

        return remedioMapper.toDetalhamentoDto(remedio);
    }


    @Transactional
    public void ativar(Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);
        remedio.ativar();
    }


    @Transactional
    public void deletar(Long id) {
        remedioRepository.deleteById(id);
    }


    @Transactional
    public void desativar(Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);
        remedio.desativar();
    }
}
