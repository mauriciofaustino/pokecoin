package br.com.bxblue.pokecoin.controller;

import br.com.bxblue.pokecoin.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private PokemonService pokemonService;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("pokemons", this.pokemonService.listByUser());
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
