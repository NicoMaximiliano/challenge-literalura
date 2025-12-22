package com.nicode.challenge_literalura.persistencia.repositorios;

import com.nicode.challenge_literalura.persistencia.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepo extends JpaRepository<Libro, Long> {
}
