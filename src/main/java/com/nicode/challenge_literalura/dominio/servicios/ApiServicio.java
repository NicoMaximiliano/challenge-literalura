package com.nicode.challenge_literalura.dominio.servicios;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiServicio {
    private final HttpClient client = HttpClient.newHttpClient();

    public String obtenerDatos(String dir) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(dir))
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}
