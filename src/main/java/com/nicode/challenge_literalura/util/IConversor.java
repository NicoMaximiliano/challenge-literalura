package com.nicode.challenge_literalura.util;

public interface IConversor {
    <T> T convertirJsonADto(String json, Class<T> clase);
}
