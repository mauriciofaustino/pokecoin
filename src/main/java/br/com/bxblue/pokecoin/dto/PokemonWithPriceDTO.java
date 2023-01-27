package br.com.bxblue.pokecoin.dto;

import br.com.bxblue.pokecoin.entity.Pokemon;
import lombok.Data;

@Data
public class PokemonWithPriceDTO {
    private Pokemon pokemon;
    private Double price;
}
