package br.com.bxblue.pokecoin.service;

import br.com.bxblue.pokecoin.consumer.MercadoBitcoinAPIConsumer;
import br.com.bxblue.pokecoin.entity.Pokemon;
import br.com.bxblue.pokecoin.entity.PokemonTransaction;
import br.com.bxblue.pokecoin.repository.TransactionRepository;
import br.com.bxblue.pokecoin.utils.PokemonPriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;

    public List<PokemonTransaction> list() {
        return transactionRepository.findByUserId(userService.getUser().getId());
    }

    public void registerTransaction(Pokemon pokemon, String type) {
        PokemonTransaction transaction = new PokemonTransaction();
        transaction.setType(type);
        transaction.setUserId(userService.getUser().getId());
        transaction.setName(pokemon.getName());
        transaction.setBaseExperience(pokemon.getBaseExperience());
        transaction.setDateCreated(new Date());
        MercadoBitcoinAPIConsumer consumer = new MercadoBitcoinAPIConsumer();
        Double btcPrice = consumer.getBtcPrice();
        transaction.setPrice(PokemonPriceCalculator.calculate(btcPrice, pokemon));
        transactionRepository.save(transaction);
    }
}
