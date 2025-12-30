package com.nicode.challenge_literalura.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicode.challenge_literalura.dominio.dtos.AutorDto;
import com.nicode.challenge_literalura.dominio.dtos.LibroDto;
import com.nicode.challenge_literalura.persistencia.entidades.Autor;
import com.nicode.challenge_literalura.persistencia.entidades.Libro;

import java.util.List;

public class Conversor implements IConversor{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertirJsonADatosCompletoLibro(String json, Class<T> clase) {

        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON vacío o nulo al intentar convertir a " + clase.getSimpleName());
        }

        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al parsear JSON a " + clase.getSimpleName(), e);
        }
    }

    public List<LibroDto> convertirListaLibroAListaLibroDto(List<Libro> libros) {
       return libros.stream()
               .map(libro -> new LibroDto(
                       libro.getTitulo(),
                       libro.getAutor().getNombre(),
                       libro.getNumDescargas(),
                       libro.getIdioma()))
               .toList();
    }

    public List<AutorDto> convertirListaAutorAListaAutorDto(List<Autor> autores) {
        return  autores.stream()
                .map(autor -> new AutorDto(
                        autor.getNombre(),
                        autor.getFecha_nacimiento(),
                        autor.getFecha_fallecimiento()))
                .toList();
    }
}
