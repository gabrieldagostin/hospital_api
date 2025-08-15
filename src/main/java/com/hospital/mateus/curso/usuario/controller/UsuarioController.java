package com.hospital.mateus.curso.usuario.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar() {

        return ResponseEntity.ok().build();
    }
}
