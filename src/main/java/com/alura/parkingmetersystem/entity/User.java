package com.alura.parkingmetersystem.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String cpf;

  private String cnh;

  @OneToMany(mappedBy = "user")
  private Set<Vehicle> vehicles = new HashSet<>();

  public User() {}

  public User(String name, String cpf, String cnh) {
  }
}
