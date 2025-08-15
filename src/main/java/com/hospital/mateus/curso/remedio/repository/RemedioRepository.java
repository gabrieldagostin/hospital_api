package com.hospital.mateus.curso.remedio.repository;

import com.hospital.mateus.curso.remedio.model.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {
                                                        List<Remedio> findAllByAtivoTrue();
}
