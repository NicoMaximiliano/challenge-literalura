package com.nicode.challenge_literalura.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Conversor implements IConversor{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertirJsonADto(String json, Class<T> clase) {
        if (json == null || json.trim().isEmpty()) {
            // Opción A: lanzar excepción para detectar el problema en quien llama
            throw new IllegalArgumentException("JSON vacío o nulo al intentar convertir a " + clase.getSimpleName());

            // Opción B (alternativa): devolver null en lugar de lanzar
            // return null;
        }

        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al parsear JSON a " + clase.getSimpleName(), e);
        }
    }


}
