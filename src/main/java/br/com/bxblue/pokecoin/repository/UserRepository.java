package br.com.bxblue.pokecoin.repository;

import br.com.bxblue.pokecoin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByUsername(String username);

  @Query("SELECT u from User u LEFT JOIN FETCH u.roles where u.username = :username")
  User findByUsernameFetchRoles(@Param("username") String username);
}
