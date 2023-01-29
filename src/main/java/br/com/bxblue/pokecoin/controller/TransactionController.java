package br.com.bxblue.pokecoin.controller;

import br.com.bxblue.pokecoin.entity.PokemonTransaction;
import br.com.bxblue.pokecoin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/transactions")
    public String list(Model model) {
        List<PokemonTransaction> transactions = this.transactionService.list();
        model.addAttribute("transactions", transactions);
        return "transaction/list";
    }

}
