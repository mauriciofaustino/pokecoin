package br.com.bxblue.pokecoin.service;

import br.com.bxblue.pokecoin.consumer.PokeAPIConsumer;
import br.com.bxblue.pokecoin.dto.PokemonDTO;
import br.com.bxblue.pokecoin.entity.Pokemon;
import br.com.bxblue.pokecoin.entity.User;
import br.com.bxblue.pokecoin.exception.ValidationException;
import br.com.bxblue.pokecoin.repository.PokemonRepository;
import br.com.bxblue.pokecoin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private UserRepository userRepository;


    public Pokemon create(PokemonDTO pokemonDTO) {
        PokeAPIConsumer consumer = new PokeAPIConsumer();
        PokemonDTO pokemonResponse = consumer.getPokemon(pokemonDTO.getName());
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonResponse.getName());
        pokemon.setBaseExperience(pokemonResponse.getBaseExperience());
        pokemon.setUserId(getUser().getId());
        pokemonRepository.save(pokemon);
        return pokemon;
    }

    public List<Pokemon> listByUser() {
        return pokemonRepository.findByUserId(getUser().getId());
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }


    public void remove(UUID id) throws ValidationException {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        if (pokemon.isEmpty()) throw new ValidationException("Pokemon nao encontrado");
        pokemonRepository.delete(pokemon.get());
    }
}
