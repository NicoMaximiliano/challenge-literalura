package com.nicode.challenge_literalura.persistencia.entidades;

import com.nicode.challenge_literalura.dominio.dtos.LibroDto;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long id;

    private String titulo;

    private String idioma;

    private Long numDescargas;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    public Libro() {
    }

    public Libro(LibroDto libroDto) {
        this.titulo = libroDto.getTitulo();
        this.idioma = libroDto.getIdioma();
        this.numDescargas = libroDto.getNumDescargas();
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(Long numDescargas) {
        this.numDescargas = numDescargas;
    }
}
