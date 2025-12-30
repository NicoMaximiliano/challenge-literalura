package com.nicode.challenge_literalura.persistencia.repositorios;

import com.nicode.challenge_literalura.persistencia.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AutorRepo extends JpaRepository<Autor, Long> {

    boolean existsByNombre(String nombre);

    Autor findByNombre(String nombre);

}
