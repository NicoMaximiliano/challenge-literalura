package com.nicode.challenge_literalura.dominio.dtos;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fecha_nacimiento,
        @JsonAlias("death_year") Integer fecha_fallecimiento
) {}
