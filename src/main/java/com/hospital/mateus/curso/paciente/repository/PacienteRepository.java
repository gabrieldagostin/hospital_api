package com.hospital.mateus.curso.paciente.repository;

import com.hospital.mateus.curso.paciente.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByAtivoTrue();
}
