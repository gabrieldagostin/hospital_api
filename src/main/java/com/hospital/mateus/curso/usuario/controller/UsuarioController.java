package com.hospital.mateus.curso.usuario.controller;

import com.hospital.mateus.curso.core.config.SecurityConfigurations;
import com.hospital.mateus.curso.usuario.dto.DadosAtualizarUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosCadastroUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosDetalhamentoUsuario;
import com.hospital.mateus.curso.usuario.dto.DadosListagemUsuario;
import com.hospital.mateus.curso.usuario.service.UsuarioService;
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
@RequestMapping("/usuarios")
@Tag(name = "Usuário", description = "Controller para salvar, editar e visualizar dados de um Usuário")
public class UsuarioController {


    private final UsuarioService usuarioService;


    @Operation(
            summary = "Cadastrar Usuário",
            description = "Recebe os dados de cadastro de um Usuário no corpo da requisição (JSON) " +
                    "e cria um novo registro na aplicação. " +
                    "Retorna um Usuário criado com seu identificador único (ID)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoUsuario.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes ou incorretos)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder) {
        DadosDetalhamentoUsuario usuario = usuarioService.cadastrar(dados);

        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();

        return ResponseEntity.created(uri).body(usuario);
    }


    @Operation(
            summary = "Detalhar Usuário",
            description = "Detalha um dos Usuário cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar seu identificador único (ID). " +
                    "Filtro dado pelo campo: ativo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso no detalhamento do Usuário",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoUsuario.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable("id") UUID id) {
        DadosDetalhamentoUsuario usuario = usuarioService.detalhar(id);

        return ResponseEntity.ok(usuario);
    }


    @Operation(
            summary = "Listar Usuários",
            description = "Lista os Usuários cadastrados na aplicação. " +
                    " Filtrados pelos registros dados como: ativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na listagem de Usuários",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosListagemUsuario.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> listar() {
        List<DadosListagemUsuario> lista = usuarioService.listar();

        return ResponseEntity.ok(lista);
    }


    @Operation(
            summary = "Atualizar Usuário",
            description = "Atualiza um dos Usuários cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar os dados novos (JSON). " +
                    "Retorna um Usuário atualizado com seu identificador único (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na atualização do Usuário",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoUsuario.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes, incorretos ou em formato inválido)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping
    public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@RequestBody @Valid DadosAtualizarUsuario dados) {
        DadosDetalhamentoUsuario usuario = usuarioService.atualizar(dados);

        return ResponseEntity.ok(usuario);
    }


    @Operation(
            summary = "Deletar Usuário",
            description = "Deleta um registro cadastrado na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao deletar um Usuário"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID id) {
        usuarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
