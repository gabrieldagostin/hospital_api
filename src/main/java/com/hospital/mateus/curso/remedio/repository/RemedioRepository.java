package com.hospital.mateus.curso.remedio.repository;

import com.hospital.mateus.curso.remedio.model.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RemedioRepository extends JpaRepository<Remedio, UUID> {
                                                        List<Remedio> findAllByAtivoTrue();
}
