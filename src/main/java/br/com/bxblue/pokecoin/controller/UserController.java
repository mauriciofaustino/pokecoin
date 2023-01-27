package br.com.bxblue.pokecoin.controller;

import br.com.bxblue.pokecoin.entity.User;
import br.com.bxblue.pokecoin.exception.ValidationException;
import br.com.bxblue.pokecoin.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CreateUserService createUserService;


    @RequestMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user, Model model) {
        User userCreated = null;
        try {
            userCreated = createUserService.execute(user);
            model.addAttribute("user", userCreated);
        } catch (ValidationException e) {
            model.addAttribute("erro", e.getErrorMessage());
            model.addAttribute("user", new User());
            return "user/new";
        }
        return "user/created";
    }

}
