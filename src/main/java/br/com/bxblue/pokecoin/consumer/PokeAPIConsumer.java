package br.com.bxblue.pokecoin.consumer;

import br.com.bxblue.pokecoin.dto.PokemonDTO;
import br.com.bxblue.pokecoin.dto.PokemonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PokeAPIConsumer {
    HttpClient client = HttpClient.newHttpClient();

    public PokemonDTO getPokemon(String id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + id))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(response.body(), PokemonDTO.class);
            } else {
                throw new RuntimeException("Error " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PokemonDTO> getPokemons() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/?limit=150"))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                PokemonResponse pokemonResponse = mapper.readValue(response.body(), PokemonResponse.class);
                return pokemonResponse.getResults();
            } else {
                throw new RuntimeException("Error " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
