package com.hospital.mateus.curso.repository;

import com.hospital.mateus.curso.model.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Entidade, tipo do Id
public interface RemedioRepository extends JpaRepository<Remedio, Long> {
                                                        List<Remedio> findAllByAtivoTrue();

    /*Quando você cria uma interface que estende JpaRepository,
    você ganha muitos métodos prontos para trabalhar com o banco de dados,
    sem precisar implementar nada.
     */
}
