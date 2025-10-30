package com.hospital.mateus.curso.remedio.controller;

import com.hospital.mateus.curso.core.config.SecurityConfigurations;
import com.hospital.mateus.curso.remedio.dto.DadosAtualizarRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosCadastroRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosDetalhamentoRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosListagemRemedio;
import com.hospital.mateus.curso.remedio.service.RemedioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@RequiredArgsConstructor
@RestController
@RequestMapping("/remedios")
@Tag(name = "Remédio", description = "Controller para salvar, editar e visualizar dados de um Remédio")
public class RemedioController {


    private final RemedioService remedioService;


    @Operation(
            summary = "Cadastrar Remédio",
            description = "Recebe os dados de cadastro de um remédio no corpo da requisição (JSON) " +
                    "e cria um novo registro na aplicação. " +
                    "Retorna um Remédio criado com seu identificador único (ID)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Remédio cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoRemedio.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes ou incorretos)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoRemedio remedio = remedioService.cadastrar(dados);

        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.id()).toUri();

        return ResponseEntity.created(uri).body(remedio);
    }


    @Operation(
            summary = "Listar Remédios",
            description = "Lista os Remédios cadastrados na aplicação. " +
                    " Filtrados pelos registros dados como: ativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na listagem de Remédios",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosListagemRemedio.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Remédios não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemRemedio>> listar() {
        var lista = remedioService.listar();

        return ResponseEntity.ok(lista);
    }


    @Operation(
            summary = "Detalhar Remédio",
            description = "Detalha um dos Remédios cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar seu identificador único (ID). " +
                    "Filtro dado pelo campo: ativo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso no detalhamento do Remédio",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoRemedio.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Remédio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoRemedio> detalhar(@PathVariable("id") UUID id) {
        DadosDetalhamentoRemedio remedio = remedioService.detalhar(id);

        return ResponseEntity.ok(remedio);
    }


    @Operation(
            summary = "Atualizar Remédio",
            description = "Atualiza um dos Remédios cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar os dados novos (JSON). " +
                    "Retorna um Remédio atualizado com seu identificador único (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na atualização do Remédio",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoRemedio.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes, incorretos ou em formato inválido)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Remédio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping
    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
        DadosDetalhamentoRemedio remedio = remedioService.atualizarRemedio(dados);

        return ResponseEntity.ok(remedio);
    }


    @Operation(
            summary = "Ativar Remédio",
            description = "Marca como true o campo ativo na tabela dos Remédios cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na ativação do Remédio",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoRemedio.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Remédio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable("id") UUID id) {
        remedioService.ativar(id);

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Deletar Remédio",
            description = "Deleta um registro cadastrado na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao deletar um Remédio"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "404", description = "Remédio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID id) {
        remedioService.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Desativar Remédio",
            description = "Marca como false o campo ativo na tabela dos Remédios cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso na desativação de um Remédios"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Remédio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable("id") UUID id) {
        remedioService.desativar(id);

        return ResponseEntity.noContent().build();
    }

}
