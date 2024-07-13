package com.example.LiteraAlura.service;

import com.example.LiteraAlura.model.Autor;
import com.example.LiteraAlura.model.EnumIdiomas;
import com.example.LiteraAlura.model.Livro;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApiLivros {


    private static final String BASE_URL = "https://gutendex.com/books";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ConsultaApiLivros() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }


    public Livro pesquisarLivro(String nomeLivro) throws URISyntaxException, IOException, InterruptedException {

        URI uri = new URI(BASE_URL + "?search=" + nomeLivro.replace(" ", "%20"));

        HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL) // Configura para seguir redirecionamentos
                .build();

        // Requisição GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        // Resposta
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode root = objectMapper.readTree(response.body());
        JsonNode results = root.path("results");


        if (results.isArray() && results.size() > 0) {
            JsonNode firstResult = results.get(0);

            Livro livro = new Livro();
            livro.setTitulo(firstResult.path("title").asText());
            livro.setIdioma(EnumIdiomas.valueOf(firstResult.path("languages").get(0).asText()));
            livro.setDowloads(firstResult.path("download_count").asInt());

            JsonNode firstAuthorNode = firstResult.path("authors").get(0);
            Autor autor = new Autor();
            autor.setNome(firstAuthorNode.path("name").asText());
            autor.setAnoDeNascimento(firstAuthorNode.path("birth_year").asInt());
            autor.setAnoDeFalecimento(firstAuthorNode.path("death_year").asInt());
            livro.setAutor(autor);

            return livro;
        }

        return null;
    }
}
