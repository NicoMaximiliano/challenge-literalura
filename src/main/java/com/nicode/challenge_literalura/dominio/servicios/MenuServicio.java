package com.nicode.challenge_literalura.dominio.servicios;

import com.nicode.challenge_literalura.dominio.dtos.LibroDTO;
import com.nicode.challenge_literalura.persistencia.entidades.Libro;
import com.nicode.challenge_literalura.persistencia.repositorios.LibroRepo;
import com.nicode.challenge_literalura.util.Conversor;

public class MenuServicio {

    private LibroRepo libroRepo;
    Conversor convertidor = new Conversor();
    ApiServicio consumoAPI = new ApiServicio();

    public MenuServicio(LibroRepo libroRepo) {
        this.libroRepo = libroRepo;
    }

    public LibroDTO getLibroPrueba() {
        var jsonResponse = consumoAPI.obtenerDatos("/books/80");

        return convertidor.convertirJsonADto(jsonResponse, LibroDTO.class);
    }

    public void guardarLibro() {
        LibroDTO libroDto = getLibroPrueba();
        Libro libro = new Libro(libroDto);

        libroRepo.save(libro);
    }
}
