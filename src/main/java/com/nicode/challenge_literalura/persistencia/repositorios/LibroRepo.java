package com.nicode.challenge_literalura.persistencia.repositorios;

import com.nicode.challenge_literalura.persistencia.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepo extends JpaRepository<Libro, Long> {

    Libro findByTituloIgnoreCase(String titulo);

    boolean existsByTituloIgnoreCase(String titulo);

    List<Libro> findByIdioma(String idioma);

    @Query(value = "SELECT l.titulo FROM libros l INNER JOIN autores a ON l.id_autor = a.id_autor WHERE LOWER(a.nombre) = LOWER(:nombreAutor)", nativeQuery = true)
    List<String> listarTitulosDeLibrosPorAutor(@Param("nombreAutor") String nombreAutor);
}
