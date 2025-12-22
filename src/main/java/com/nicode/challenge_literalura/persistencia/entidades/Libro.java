package com.nicode.challenge_literalura.persistencia.entidades;

import com.nicode.challenge_literalura.dominio.dtos.LibroDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String titulo;

    String idiomas;

    Long numDescargas;

    @ManyToOne
    Autor autor;

    public Libro() {
    }

    public Libro(LibroDTO libroDTO) {
        this.titulo = libroDTO.titulo();
        this.idiomas = libroDTO.idiomas();
        this.numDescargas = libroDTO.numDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Long getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(Long numDescargas) {
        this.numDescargas = numDescargas;
    }


}
