package com.hospital.mateus.curso.medico.repository;

import com.hospital.mateus.curso.medico.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {
    List<Medico> findAllByAtivoTrue();
}
