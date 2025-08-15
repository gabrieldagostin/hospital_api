package com.hospital.mateus.curso.medico.repository;

import com.hospital.mateus.curso.medico.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findAllByAtivoTrue();
}
