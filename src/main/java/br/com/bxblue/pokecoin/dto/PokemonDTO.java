package br.com.bxblue.pokecoin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDTO {
    private Long id;
    private String name;
    private Long baseExperience;
}
