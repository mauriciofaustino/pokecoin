package br.com.bxblue.pokecoin.service;

import br.com.bxblue.pokecoin.consumer.MercadoBitcoinAPIConsumer;
import br.com.bxblue.pokecoin.dto.PokemonWithPriceDTO;
import br.com.bxblue.pokecoin.entity.Pokemon;
import br.com.bxblue.pokecoin.utils.PokemonPriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    @Autowired
    private PokemonService pokemonService;

    public List<PokemonWithPriceDTO> listPokemonsWithPrice() {
        List<Pokemon> pokemons = pokemonService.listByUser();
        MercadoBitcoinAPIConsumer consumer = new MercadoBitcoinAPIConsumer();
        Double bitCoinPrice = consumer.getBtcPrice();
        List<PokemonWithPriceDTO> pokemonsPrice = new ArrayList<>();
        for (Pokemon pokemon : pokemons) {
            PokemonWithPriceDTO dto = new PokemonWithPriceDTO();
            dto.setPokemon(pokemon);
            dto.setPrice(PokemonPriceCalculator.calculate(bitCoinPrice, pokemon));
            pokemonsPrice.add(dto);
        }
        return pokemonsPrice;
    }


}
