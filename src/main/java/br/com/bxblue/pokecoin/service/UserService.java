package br.com.bxblue.pokecoin.service;


import br.com.bxblue.pokecoin.entity.User;
import br.com.bxblue.pokecoin.repository.UserRepository;
import br.com.bxblue.pokecoin.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User existsUser = userRepository.findByUsernameFetchRoles(username);
        if (existsUser == null) {
            throw new UsernameNotFoundException("User does not exists!");
        }
        return UserPrincipal.create(existsUser);
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }

}
