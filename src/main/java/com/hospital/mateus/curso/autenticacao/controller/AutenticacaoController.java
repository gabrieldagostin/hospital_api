package com.hospital.mateus.curso.autenticacao.controller;

import com.hospital.mateus.curso.autenticacao.dto.DadosAutenticacao;
import com.hospital.mateus.curso.autenticacao.dto.DadosTokenJWT;
import com.hospital.mateus.curso.autenticacao.jwt.TokenService;
import com.hospital.mateus.curso.usuario.model.Usuario;
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
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autenticacao = authenticationManager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((Usuario)
        autenticacao.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
