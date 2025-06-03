package com.hospital.mateus.curso.repository;

import com.hospital.mateus.curso.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByAtivoTrue();
}
