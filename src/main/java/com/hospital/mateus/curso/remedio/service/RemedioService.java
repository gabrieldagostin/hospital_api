package com.hospital.mateus.curso.remedio.service;

import com.hospital.mateus.curso.remedio.dto.DadosAtualizarRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosCadastroRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosDetalhamentoRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosListagemRemedio;
import com.hospital.mateus.curso.remedio.model.Remedio;
import com.hospital.mateus.curso.remedio.repository.RemedioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RemedioService {


    private final RemedioRepository remedioRepository;

    private final ModelMapper modelMapper;


    @Transactional
    public DadosDetalhamentoRemedio cadastrar(@Valid DadosCadastroRemedio dados) {
        Remedio remedio = modelMapper.map(dados, Remedio.class);
        remedioRepository.save(remedio);

        return modelMapper.map(remedio, DadosDetalhamentoRemedio.class);
    }


    public List<DadosListagemRemedio> listar() {
        return remedioRepository.findAllByAtivoTrue().stream().map(
                remedio -> modelMapper.map(remedio, DadosListagemRemedio.class)).toList();
    }


    public DadosDetalhamentoRemedio detalhar(Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);

        return modelMapper.map(remedio, DadosDetalhamentoRemedio.class);
    }


    @Transactional
    public DadosDetalhamentoRemedio atualizarRemedio(DadosAtualizarRemedio dados) {
        Remedio remedio = remedioRepository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);

        return modelMapper.map(remedio, DadosDetalhamentoRemedio.class);
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
