package br.com.bxblue.pokecoin.service;

import br.com.bxblue.pokecoin.consumer.PokeAPIConsumer;
import br.com.bxblue.pokecoin.dto.PokemonDTO;
import br.com.bxblue.pokecoin.entity.Pokemon;
import br.com.bxblue.pokecoin.exception.ValidationException;
import br.com.bxblue.pokecoin.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PokemonService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PokemonRepository pokemonRepository;

    public Pokemon create(PokemonDTO pokemonDTO) {
        PokeAPIConsumer consumer = new PokeAPIConsumer();
        PokemonDTO pokemonResponse = consumer.getPokemon(pokemonDTO.getName());
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonResponse.getName());
        pokemon.setBaseExperience(pokemonResponse.getBaseExperience());
        pokemon.setUserId(userService.getUser().getId());
        pokemonRepository.save(pokemon);
        transactionService.registerTransaction(pokemon, "Registro de compra");
        return pokemon;
    }

    public List<Pokemon> listByUser() {
        return pokemonRepository.findByUserId(userService.getUser().getId());
    }

    public void remove(UUID id) throws ValidationException {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        if (!pokemon.isPresent()) throw new ValidationException("Pokemon nao encontrado");
        pokemonRepository.delete(pokemon.get());
        transactionService.registerTransaction(pokemon.get(), "Registro de venda");
    }
}
