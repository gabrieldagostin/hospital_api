package com.hospital.mateus.curso.remedio.service;

import com.hospital.mateus.curso.remedio.dto.DadosAtualizarRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosCadastroRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosListagemRemedio;
import com.hospital.mateus.curso.remedio.model.Remedio;
import com.hospital.mateus.curso.remedio.repository.RemedioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RemedioService {

    private final RemedioRepository remedioRepository;


    public Remedio cadastrar(@Valid DadosCadastroRemedio dados) {
        Remedio remedio = new Remedio(dados);
        remedioRepository.save(remedio);

        return remedio;
    }


    public List<DadosListagemRemedio> listar() {
        return remedioRepository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
    }


    public Remedio detalhar(Long id) {
        return remedioRepository.getReferenceById(id);
    }


    public Remedio atualizarRemedio(DadosAtualizarRemedio dados) {
        Remedio remedio = remedioRepository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);

        return remedio;
    }


    public void ativar(Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);
        remedio.ativar();
    }


    public void deletar(Long id) {
        remedioRepository.deleteById(id);
    }


    public void desativar(Long id) {
        Remedio remedio = remedioRepository.getReferenceById(id);
        remedio.desativar();
    }
}
