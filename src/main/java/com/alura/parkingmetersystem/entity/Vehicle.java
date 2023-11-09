package com.alura.parkingmetersystem.entity;

import com.alura.parkingmetersystem.entity.model.VehicleDTO;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String model;

  private String carPlate;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Getter
  @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
  private Set<ParkingSession> parkingSessions;

  public Vehicle(VehicleDTO vehicleDTO) {
  }

  public Vehicle() {

  }
}
