package br.com.bxblue.pokecoin.consumer;

import br.com.bxblue.pokecoin.dto.PokemonDTO;
import br.com.bxblue.pokecoin.dto.PokemonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PokeAPIConsumer {

    public PokemonDTO getPokemon(String id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://pokeapi.co/api/v2/pokemon/" + id;
            String jsonResponse = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            return mapper.readValue(jsonResponse, PokemonDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PokemonDTO> getPokemons() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://pokeapi.co/api/v2/pokemon/?limit=150";
            String jsonResponse = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            PokemonResponse pokemonResponse = mapper.readValue(jsonResponse, PokemonResponse.class);
            return pokemonResponse.getResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
