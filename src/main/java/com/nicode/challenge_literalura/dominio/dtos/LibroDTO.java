package com.nicode.challenge_literalura.dominio.dtos;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") String idiomas,
        @JsonAlias("download_count") Long numDescargas
) {}
