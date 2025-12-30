package com.nicode.challenge_literalura.persistencia.entidades;

import com.nicode.challenge_literalura.dominio.dtos.AutorDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long id;

    private String nombre;

    private Integer fecha_nacimiento;

    private Integer fecha_fallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(AutorDto autorDto) {
        this.nombre = autorDto.getNombre();
        this.fecha_nacimiento = autorDto.getFecha_nacimiento();
        this.fecha_fallecimiento = autorDto.getFecha_fallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Integer fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Integer getFecha_fallecimiento() {
        return fecha_fallecimiento;
    }

    public void setFecha_fallecimiento(Integer fecha_fallecimiento) {
        this.fecha_fallecimiento = fecha_fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}