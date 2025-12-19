package com.nicode.challenge_literalura;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String URL_BASE = "https://gutendex.com";

    public static String obtenerDatos(String urlEndpoint) {
        String urlCompleta = URL_BASE + urlEndpoint;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlCompleta))
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}
