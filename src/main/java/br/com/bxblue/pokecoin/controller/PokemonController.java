package br.com.bxblue.pokecoin.controller;

import br.com.bxblue.pokecoin.consumer.PokeAPIConsumer;
import br.com.bxblue.pokecoin.dto.PokemonDTO;
import br.com.bxblue.pokecoin.entity.Pokemon;
import br.com.bxblue.pokecoin.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @RequestMapping("/pokemon/new")
    public String newPokemon(Model model) {
        model.addAttribute("dto", new PokemonDTO());
        PokeAPIConsumer consumer = new PokeAPIConsumer();
        model.addAttribute("pokemons", consumer.getPokemons());
        return "pokemon/new";
    }

    @PostMapping("/pokemon/create")
    public String create(@ModelAttribute("user") PokemonDTO pokemonDTO, Model model) {
        Pokemon pokemonCreated = this.pokemonService.create(pokemonDTO);
        model.addAttribute("message", "Pokemon registrado com sucesso");
        return "pokemon/message";
    }
}
