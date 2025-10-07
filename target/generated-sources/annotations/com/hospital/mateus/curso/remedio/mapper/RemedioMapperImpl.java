package com.hospital.mateus.curso.remedio.mapper;

import com.hospital.mateus.curso.core.enums.Laboratorio;
import com.hospital.mateus.curso.core.enums.Via;
import com.hospital.mateus.curso.remedio.dto.DadosCadastroRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosDetalhamentoRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosListagemRemedio;
import com.hospital.mateus.curso.remedio.model.Remedio;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-28T11:07:26-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class RemedioMapperImpl implements RemedioMapper {

    @Override
    public Remedio toEntity(DadosCadastroRemedio dto) {
        if ( dto == null ) {
            return null;
        }

        Remedio remedio = new Remedio();

        remedio.setNome( dto.nome() );
        remedio.setVia( dto.via() );
        remedio.setLote( dto.lote() );
        remedio.setQuantidade( dto.quantidade() );
        remedio.setValidade( dto.validade() );
        remedio.setLaboratorio( dto.laboratorio() );

        return remedio;
    }

    @Override
    public DadosDetalhamentoRemedio toDetalhamentoDto(Remedio remedio) {
        if ( remedio == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        Via via = null;
        String lote = null;
        int quantidade = 0;
        LocalDate validade = null;
        Laboratorio laboratorio = null;
        boolean ativo = false;

        id = remedio.getId();
        nome = remedio.getNome();
        via = remedio.getVia();
        lote = remedio.getLote();
        quantidade = remedio.getQuantidade();
        validade = remedio.getValidade();
        laboratorio = remedio.getLaboratorio();
        ativo = remedio.isAtivo();

        DadosDetalhamentoRemedio dadosDetalhamentoRemedio = new DadosDetalhamentoRemedio( id, nome, via, lote, quantidade, validade, laboratorio, ativo );

        return dadosDetalhamentoRemedio;
    }

    @Override
    public DadosListagemRemedio toListagemDto(Remedio remedio) {
        if ( remedio == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        Via via = null;
        String lote = null;
        Laboratorio laboratorio = null;
        LocalDate validade = null;

        id = remedio.getId();
        nome = remedio.getNome();
        via = remedio.getVia();
        lote = remedio.getLote();
        laboratorio = remedio.getLaboratorio();
        validade = remedio.getValidade();

        DadosListagemRemedio dadosListagemRemedio = new DadosListagemRemedio( id, nome, via, lote, laboratorio, validade );

        return dadosListagemRemedio;
    }
}
