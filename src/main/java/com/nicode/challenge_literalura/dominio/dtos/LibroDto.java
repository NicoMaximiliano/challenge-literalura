package com.nicode.challenge_literalura.dominio.dtos;

public class LibroDto {

    private String titulo;
    private String autor;
    private Long numDescargas;
    private String idioma;

    public LibroDto() {}

    public LibroDto(String titulo, String autor, Long numDescargas, String idioma) {
        this.titulo = titulo;
        this.autor = autor;
        this.numDescargas = numDescargas;
        this.idioma = idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(Long numDescargas) {
        this.numDescargas = numDescargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return
                "\nTitulo: " + titulo + "\n" +
                "- Autor: " + autor + "\n" +
                "- Numero de descargas: " + numDescargas + "\n" +
                "- Idioma: " + idioma;
    }
}
