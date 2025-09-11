package com.hospital.mateus.curso.medico.controller;

import java.util.List;

import com.hospital.mateus.curso.config.SecurityConfigurations;
import com.hospital.mateus.curso.medico.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.hospital.mateus.curso.medico.dto.DadosAtualizarMedico;
import com.hospital.mateus.curso.medico.dto.DadosCadastroMedico;
import com.hospital.mateus.curso.medico.dto.DadosDetalhamentoMedico;
import com.hospital.mateus.curso.medico.dto.DadosListagemMedico;
import jakarta.validation.Valid;

@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@RequiredArgsConstructor
@RestController
@RequestMapping("/medicos")
@Tag(name = "Médico", description = "Controller para salvar, editar e visualizar dados de um Médico")
public class MedicoController {


    private final MedicoService medicoService;


    @Operation(
            summary = "Cadastrar Médico",
            description = "Recebe os dados de cadastro de um médico no corpo da requisição (JSON) " +
                    "e cria um novo registro na aplicação. " +
                    "Retorna um Médico criado com seu identificador único (ID)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Médico cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoMedico.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes ou incorretos)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoMedico medico = medicoService.cadastrar(dados);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.id()).toUri();

        return ResponseEntity.created(uri).body(medico);
    }


    @Operation(
            summary = "Listar Médicos",
            description = "Lista os Médicos cadastrados na aplicação. " +
                    " Filtrados pelos registros dados como: ativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na listagem de Médicos",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosListagemMedico.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Médicos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemMedico>> listar() {
        List<DadosListagemMedico> lista = medicoService.listar();

        return ResponseEntity.ok(lista);
    }


    @Operation(
            summary = "Detalhar Médico",
            description = "Detalha um dos Médicos cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar seu identificador único (ID). " +
                    "Filtro dado pelo campo: ativo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso no detalhamento do Médico",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoMedico.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable("id") long id) {
        DadosDetalhamentoMedico medico = medicoService.detalhar(id);

        return ResponseEntity.ok(medico);
    }


    @Operation(
            summary = "Atualizar Médico",
            description = "Atualiza um dos Médicos cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar os dados novos (JSON). " +
                    "Retorna um Médico atualizado com seu identificador único (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na atualização do Médico",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoMedico.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes, incorretos ou em formato inválido)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        DadosDetalhamentoMedico medico = medicoService.atualizar(dados);

        return ResponseEntity.ok(medico);
    }


    @Operation(
            summary = "Ativar Médico",
            description = "Marca como true o campo ativo na tabela dos Médicos cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na ativação do Médico",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoMedico.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable("id") long id) {
        medicoService.ativar(id);

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Deletar Médico",
            description = "Deleta um registro cadastrado na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao deletar um Médico"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        medicoService.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Desativar Médico",
            description = "Marca como false o campo ativo na tabela dos Médicos cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso na desativação de um Médico"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable("id") long id) {
        medicoService.desativar(id);

        return ResponseEntity.noContent().build();
    }

}
