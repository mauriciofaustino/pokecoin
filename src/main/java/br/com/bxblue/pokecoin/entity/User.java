package br.com.bxblue.pokecoin.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private UUID id;

  @NotNull
  private String username;

  @NotNull
  private String password;

  @ManyToMany
  private List<Role> roles;
}
