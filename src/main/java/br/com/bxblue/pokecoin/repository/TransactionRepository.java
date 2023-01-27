package br.com.bxblue.pokecoin.repository;

import br.com.bxblue.pokecoin.entity.PokemonTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<PokemonTransaction, UUID> {

    List<PokemonTransaction> findByUserId(UUID id);
}
