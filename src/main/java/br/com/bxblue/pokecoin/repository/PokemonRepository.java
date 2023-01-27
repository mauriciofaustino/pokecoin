package br.com.bxblue.pokecoin.repository;

import br.com.bxblue.pokecoin.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {


    List<Pokemon> findByUserId(UUID id);
}
