package com.hospital.mateus.curso.paciente.controller;

import com.hospital.mateus.curso.config.SecurityConfigurations;
import com.hospital.mateus.curso.paciente.dto.*;
import com.hospital.mateus.curso.paciente.service.PacienteService;
import com.hospital.mateus.curso.remedio.dto.DadosListagemRemedio;
import com.hospital.mateus.curso.remedio.dto.DadosRemedio;
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

@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@RequiredArgsConstructor
@RestController
@RequestMapping("/pacientes")
@Tag(name = "Paciente", description = "Controller para salvar, editar e visualizar dados de um Paciente")
public class PacienteController {


    private final PacienteService pacienteService;


    @Operation(
            summary = "Cadastrar Paciente",
            description = "Recebe os dados de cadastro de um Paciente no corpo da requisição (JSON) " +
                    "e cria um novo registro na aplicação. " +
                    "Retorna um Paciente criado com seu identificador único (ID)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoPaciente.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes ou incorretos)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoPaciente paciente = pacienteService.cadastrar(dados);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.id()).toUri();

        return ResponseEntity.created(uri).body(paciente);
    }


    @Operation(
            summary = "Listar Pacientes",
            description = "Lista os Pacientes cadastrados na aplicação. " +
                    " Filtrados pelos registros dados como: ativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na listagem de Pacientes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosListagemPaciente.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Pacientes não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemPaciente>> listar() {
        List<DadosListagemPaciente> lista = pacienteService.listar();

        return ResponseEntity.ok(lista);
    }


    @Operation(
            summary = "Detalhar Paciente",
            description = "Detalha um dos Pacientes cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar seu identificador único (ID). " +
                    "Filtro dado pelo campo: ativo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso no detalhamento do Paciente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoPaciente.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable("id") long id) {
        DadosDetalhamentoPaciente paciente = pacienteService.detalhar(id);

        return ResponseEntity.ok(paciente);
    }


    @Operation(
            summary = "Atualizar Paciente",
            description = "Atualiza um dos Pacientes cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar os dados novos (JSON). " +
                    "Retorna um Paciente atualizado com seu identificador único (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na atualização do Paciente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoPaciente.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes, incorretos ou em formato inválido)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizarPaciente dados) {
        DadosDetalhamentoPaciente paciente = pacienteService.atualizar(dados);

        return ResponseEntity.ok(paciente);
    }


    @Operation(
            summary = "Ativar Paciente",
            description = "Marca como true o campo ativo na tabela dos Pacientes cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na ativação do Paciente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoPaciente.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable("id") long id) {
        pacienteService.ativar(id);

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Desativar Paciente",
            description = "Marca como false o campo ativo na tabela dos Pacientes cadastrados na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso na desativação de um Paciente"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable("id") long id) {
        pacienteService.desativar(id);

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Deletar Paciente",
            description = "Deleta um registro cadastrado na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único  (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao deletar um Paciente"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        pacienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Associar Remédio ao Paciente",
            description = "Associa um registro de Remédio a um Paciente cadastrado na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao associar um Remédio ao Paciente"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente ou Remédio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PatchMapping("/associar-remedio")
    public ResponseEntity<Void> associarRemedio(@RequestBody @Valid DadosAssociarRemedioPaciente dados) {
        pacienteService.associarRemedio(dados);

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Remover Remédio do Paciente",
            description = "Remove um registro de Remédio de um Paciente cadastrado na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao remover um Remédio do Paciente"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente ou Remédio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/remover-remedio")
    public ResponseEntity<Void> removerRemedio(@RequestBody @Valid DadosAssociarRemedioPaciente dados) {
        pacienteService.removerRemedio(dados);

        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Listar Remédios dos Pacientes",
            description = "Lista os Remédios associados aos Pacientes cadastrados na aplicação. " +
                    " Filtrados pelos registros dados como: ativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na listagem de Remédios dos Pacientes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosListagemRemedio.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),
            @ApiResponse(responseCode = "404", description = "Paciente ou Remédios não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}/remedios")
    public ResponseEntity<List<DadosRemedio>> listarRemedios(@PathVariable("id") long id) {
        List<DadosRemedio> lista = pacienteService.listarRemedios(id);

        return ResponseEntity.ok(lista);
    }


    @Operation(
            summary = "Associar Médico ao Paciente",
            description = "Associa um registro de Médico a um Paciente cadastrado na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao associar um Médico ao Paciente"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente ou Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PatchMapping("/associar-medico")
    public ResponseEntity<Void> associarMedico(@RequestBody @Valid DadosAssociarMedicoPaciente dados) {
        pacienteService.associarMedico(dados);

        return ResponseEntity.noContent().build();

    }


    @Operation(
            summary = "Remover Médico do Paciente",
            description = "Remove um registro de Médico de um Paciente cadastrado na aplicação, " +
                    "utilizando o corpo da requisição para enviar o identificador único (ID).")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao remover o Médico do Paciente"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (Párametros inválidos ou mal formados)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "403", description = "Usuário autenticado, mas sem permissão para executar a ação"),            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente ou Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/remover-medico")
    public ResponseEntity<Void> removerMedico(@RequestBody @Valid DadosRemoverMedicoPaciente dados) {
        pacienteService.removerMedico(dados);

        return ResponseEntity.noContent().build();
    }
}
