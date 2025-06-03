package com.hospital.mateus.curso.repository;

import com.hospital.mateus.curso.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findAllByAtivoTrue();
}
