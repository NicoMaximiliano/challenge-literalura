package com.nicode.challenge_literalura.dominio.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosCompletoLibro(
        @JsonAlias("results") List<DatosLibro> resultados
) {}
