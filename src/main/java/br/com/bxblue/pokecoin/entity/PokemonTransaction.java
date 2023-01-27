package br.com.bxblue.pokecoin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "pokemon_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonTransaction {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;
  private String type;

  private Long baseExperience;
  private Double price;

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="user_id",insertable=false,updatable=false)
  private User user;

  @Column(name="date_created")
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date dateCreated;
}
