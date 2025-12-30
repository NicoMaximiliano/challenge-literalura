package com.nicode.challenge_literalura.dominio.dtos;

import java.util.List;

public class AutorDto {

    private String nombre;
    private Integer fecha_nacimiento;
    private Integer fecha_fallecimiento;

    private List<String> nombresLibros;

    public AutorDto() {}

    public AutorDto(String nombre, Integer fecha_nacimiento, Integer fecha_fallecimiento) {
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_fallecimiento = fecha_fallecimiento;
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

    public List<String> getNombresLibros() {
        return nombresLibros;
    }

    public void setNombresLibros(List<String> nombresLibros) {
        this.nombresLibros = nombresLibros;
    }

    @Override
    public String toString() {
        return
                "\nNombre: " + nombre + "\n" +
                "- Fecha de nacimiento: " + fecha_nacimiento + "\n" +
                "- Fecha de fallecimiento: " + fecha_fallecimiento + "\n" +
                "- Libros: " + nombresLibros;
    }
}
