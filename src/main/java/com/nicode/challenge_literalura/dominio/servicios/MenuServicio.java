package com.nicode.challenge_literalura.dominio.servicios;

import com.nicode.challenge_literalura.dominio.dtos.*;
import com.nicode.challenge_literalura.persistencia.entidades.Autor;
import com.nicode.challenge_literalura.persistencia.entidades.Libro;
import com.nicode.challenge_literalura.persistencia.repositorios.AutorRepo;
import com.nicode.challenge_literalura.persistencia.repositorios.LibroRepo;
import com.nicode.challenge_literalura.util.Conversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class MenuServicio {
    private final Scanner entrada = new Scanner(System.in);
    private final Conversor convertidor = new Conversor();

    @Autowired
    private ApiServicio consumoAPI;

    @Autowired
    private LibroRepo libroRepo;

    @Autowired
    private AutorRepo autorRepo;

    private DatosCompletoLibro datos;

    public void mostrarMenu() {
        var opcion = 0;

        while (opcion != 6) {
            try{
                System.out.println("\n----- Menú -----");
                var menu = """
                1. Buscar libro por titulo
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar autores vivos en un determinado año
                5. Listar libros por idioma
                6. Salir
                """;
                System.out.println(menu);
                System.out.print("Seleccione una opción: ");
                opcion = entrada.nextInt();
                entrada.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese el título del libro a buscar: ");
                        String titulo = entrada.nextLine();

                        if (existeElLibro(titulo)){
                            mostrarLibro(titulo);
                        }
                        else{
                            try{
                                obtenerDatos(titulo);
                                guardarDatos();
                                mostrarLibro(titulo);
                            }
                            catch (NullPointerException e){
                                System.out.println("\nNo se encontraron datos para el libro solicitado.");
                            }
                        }
                    }
                    case 2 -> {
                        listarLibrosRegistrados();
                    }
                    case 3 -> {
                        listarAutoresRegistrados();
                    }
                    case 4 -> {
                        try{
                            System.out.print("Ingrese el año para listar autores vivos: ");
                            int anio = entrada.nextInt();
                            entrada.nextLine(); // Limpiar el buffer
                            listarAutoresVivosEnDeterminadoAnio(anio);
                        }
                        catch (InputMismatchException e){
                            System.out.println("\nError: Debe ingresar un número entero para el año.");
                            entrada.nextLine(); // Limpiar el buffer
                        }
                    }
                    case 5 -> {
                        System.out.println("\n----- Idiomas disponibles -----");
                        boolean idiomaValido = false;

                        var idiomas = """
                            es - español
                            en - inglés
                            fr - francés
                            pt - portugués
                            """;
                        System.out.println(idiomas);
                        System.out.print("Ingrese el idioma: ");
                        String idioma = entrada.nextLine();

                        for (IdiomaEnum i : IdiomaEnum.values()) {
                            if (i.getCodigo().equalsIgnoreCase(idioma)) {
                                idiomaValido = true;
                                break;
                            }
                        }

                        if (idiomaValido){
                            listarLibrosPorIdioma(idioma);
                        }
                        else {
                            System.out.println("\nIdioma no válido. Por favor, intente de nuevo.");
                        }
                    }
                    case 6 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("\nOpción no válida. Por favor, intente de nuevo.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("\nError: Debe ingresar un número entero para la opción del menú.");
                entrada.nextLine(); // Limpiar el buffer
            }
        }
    }

    public boolean existeElLibro(String tituloLibro){
        return libroRepo.existsByTituloIgnoreCase(tituloLibro);
    }

    public void obtenerDatos(String tituloLibro) {
        String URL_BASE = "https://gutendex.com";
        var jsonResponse = consumoAPI.obtenerDatos(URL_BASE + "/books/?search=" + tituloLibro.toLowerCase().replace(" ","+"));

        datos = convertidor.convertirJsonADatosCompletoLibro(jsonResponse, DatosCompletoLibro.class);
    }

    public void guardarDatos(){
        LibroDto libroDto = datos.resultados().stream()
                .map(l -> new LibroDto(
                        l.titulo(),
                        l.autor().get(0).nombre(),
                        l.numDescargas(),
                        l.idiomas().get(0)))
                .findFirst()
                .orElse(null);

        AutorDto autorDto = datos.resultados().stream()
                .map(l -> new AutorDto(
                        l.autor().get(0).nombre(),
                        l.autor().get(0).fecha_nacimiento(),
                        l.autor().get(0).fecha_fallecimiento()))
                .findFirst()
                .orElse(null);

        Libro libro = new Libro(libroDto);
        Autor autor = new Autor(autorDto);

        if(existeElAutor(libroDto)){
            autor = autorRepo.findByNombre(libroDto.getAutor());
        }

        autorRepo.save(autor);
        libro.setAutor(autor); // Establecer la relación entre libro y autor
        libroRepo.save(libro);
    }

    public boolean existeElAutor(LibroDto libroDto){
        return autorRepo.existsByNombre(libroDto.getAutor());
    }

    public void mostrarLibro(String titulo){
        Libro libro = libroRepo.findByTituloIgnoreCase(titulo);

        LibroDto libroDto = new LibroDto(
                        libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.getNumDescargas(),
                        libro.getIdioma());

        System.out.println("\n----- Libro -----");
        System.out.println(libroDto);
    }

    public void listarLibrosRegistrados(){
        List<LibroDto> librosDtos = convertidor.convertirListaLibroAListaLibroDto(libroRepo.findAll());

        System.out.println("\n----- Libros Registrados -----");
        librosDtos.forEach(System.out::println);
    }

    public void listarAutoresRegistrados(){
        List<AutorDto> autoresDtos = convertidor.convertirListaAutorAListaAutorDto(autorRepo.findAll());

        autoresDtos.forEach(autorDto -> autorDto.setNombresLibros(libroRepo.listarTitulosDeLibrosPorAutor(autorDto.getNombre())));

        System.out.println("\n----- Autores Registrados -----");
        autoresDtos.forEach(System.out::println);
    }

    public void listarAutoresVivosEnDeterminadoAnio(int anio){
        List<AutorDto> autoresDtos = autorRepo.findAll().stream()
                .filter(autor -> (autor.getFecha_nacimiento() != null && autor.getFecha_nacimiento() <= anio) &&
                        (autor.getFecha_fallecimiento() != null && autor.getFecha_fallecimiento() >= anio))
                .map(autor -> new AutorDto(
                        autor.getNombre(),
                        autor.getFecha_nacimiento(),
                        autor.getFecha_fallecimiento()))
                .toList();

        if (autoresDtos.isEmpty()){
            System.out.println("\n----- Autores vivos en el año: " + anio + " -----");
            System.out.println("\nNo se encontraron autores vivos en el año especificado.");
        }
        else{
            autoresDtos.forEach(autorDto -> autorDto.setNombresLibros(libroRepo.listarTitulosDeLibrosPorAutor(autorDto.getNombre())));
            System.out.println("\n----- Autores vivos en el año: " + anio + " -----");
            autoresDtos.forEach(System.out::println);
        }
    }

    public void listarLibrosPorIdioma(String idioma){
        List<LibroDto> librosDtos = convertidor.convertirListaLibroAListaLibroDto(libroRepo.findByIdioma(idioma));

        System.out.println("\n----- Libros en idioma: " + idioma + " -----");
        if (librosDtos.isEmpty()){
            System.out.println("\nNo se encontraron libros en el idioma especificado.");
        }
        else{
            librosDtos.forEach(System.out::println);
        }
    }

}
