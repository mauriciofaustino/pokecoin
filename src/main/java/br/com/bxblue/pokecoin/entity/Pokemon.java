package br.com.bxblue.pokecoin.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pokemons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  private Long baseExperience;

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="user_id",insertable=false,updatable=false)
  private User user;
}
