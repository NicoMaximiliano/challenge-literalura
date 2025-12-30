package com.nicode.challenge_literalura.util;

public interface IConversor {
    <T> T convertirJsonADatosCompletoLibro(String json, Class<T> clase);

}
