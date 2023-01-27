package br.com.bxblue.pokecoin.service;

import br.com.bxblue.pokecoin.entity.User;
import br.com.bxblue.pokecoin.exception.UserValidationException;
import br.com.bxblue.pokecoin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User execute(User user) throws UserValidationException {
        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword()) ) {
            throw new UserValidationException("Preencha o formulário corretamente.");
        }
        User existsUser = userRepository.findByUsername(user.getUsername());

        if (existsUser != null) {
            throw new UserValidationException("Usuário já existe.");
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));

        User createdUser = userRepository.save(user);

        return createdUser;
    }

}
