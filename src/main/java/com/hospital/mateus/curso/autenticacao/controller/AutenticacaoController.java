package com.hospital.mateus.curso.autenticacao.controller;

import com.hospital.mateus.curso.autenticacao.dto.DadosAutenticacao;
import com.hospital.mateus.curso.autenticacao.dto.DadosTokenJWT;
import com.hospital.mateus.curso.autenticacao.jwt.TokenService;
import com.hospital.mateus.curso.config.SecurityConfigurations;
import com.hospital.mateus.curso.usuario.model.Usuario;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
@Tag(name = "Autenticação", description = "Controlador para fazer a autenticação de usuários")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Operation(
            summary = "Efetua o Login",
            description = "Método para logar o usuário na aplicação, requisitando seu login e senha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosTokenJWT.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados ausentes ou incorretos)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autenticacao = authenticationManager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((Usuario)
                autenticacao.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
