package br.com.bxblue.pokecoin.controller;

import br.com.bxblue.pokecoin.dto.PokemonWithPriceDTO;
import br.com.bxblue.pokecoin.service.HomeService;
import br.com.bxblue.pokecoin.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private HomeService homeService;

    @RequestMapping("/home")
    public String home(Model model) {
        List<PokemonWithPriceDTO> pokemons = this.homeService.listPokemonsWithPrice();
        model.addAttribute("pokemons", pokemons);
        model.addAttribute("wallet", pokemons.stream()
                .mapToDouble(PokemonWithPriceDTO::getPrice)
                .sum());
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login?error")
    public String loginError() {
        return "login";
    }
}
