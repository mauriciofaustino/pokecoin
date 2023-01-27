package br.com.bxblue.pokecoin.utils;

import br.com.bxblue.pokecoin.entity.Pokemon;

public class PokemonPriceCalculator {
    private static final double SATOSHI = 0.00000001;

    public static double calculate(Double bitCoinPrice, Pokemon pokemon) {
        Long baseExperience = pokemon.getBaseExperience();
        if (bitCoinPrice == null) bitCoinPrice = 0d;
        if (baseExperience == null) baseExperience = 0L;
        return baseExperience * SATOSHI * bitCoinPrice;
    }
}
