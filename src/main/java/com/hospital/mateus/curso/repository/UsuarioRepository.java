package com.hospital.mateus.curso.repository;

import com.hospital.mateus.curso.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
