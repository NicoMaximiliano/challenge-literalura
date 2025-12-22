package com.nicode.challenge_literalura.dominio.servicios;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiServicio {
    private HttpClient client = HttpClient.newHttpClient();
    private String URL_BASE = "https://gutendex.com";

    public String obtenerDatos(String urlEndpoint) {
        URI direccion = URI.create(URL_BASE + urlEndpoint);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
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
