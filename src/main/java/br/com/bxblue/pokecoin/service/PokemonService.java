package br.com.bxblue.pokecoin.service;

import br.com.bxblue.pokecoin.dto.PokemonDTO;
import br.com.bxblue.pokecoin.entity.Pokemon;
import br.com.bxblue.pokecoin.entity.User;
import br.com.bxblue.pokecoin.exception.UserValidationException;
import br.com.bxblue.pokecoin.repository.PokemonRepository;
import br.com.bxblue.pokecoin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;


    public Pokemon create(PokemonDTO pokemonDTO) {


        return null;
    }

}
