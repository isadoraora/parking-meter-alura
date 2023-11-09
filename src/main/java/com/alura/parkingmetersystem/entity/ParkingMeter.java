package com.alura.parkingmetersystem.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "tb_parking_meter")
public class ParkingMeter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String code;

  @Column(nullable = false)
  private String location;

  @Enumerated(EnumType.STRING)
  private ParkingMeterStatus status;

  @OneToMany(mappedBy = "parkingMeter", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ParkingSession> parkingSessions = new HashSet<>();

  public ParkingMeter() {
  }

  public ParkingMeter(String code, String location, ParkingMeterStatus status) {
    this.code = code;
    this.location = location;
    this.status = status;
  }

  public enum ParkingMeterStatus {
    ACTIVE,
    OUT_OF_SERVICE,
    OCCUPIED,
    AVAILABLE
  }

  public void addParkingSession(ParkingSession session) {
    parkingSessions.add(session);
    session.setParkingMeter(this);
  }

  public void removeParkingSession(ParkingSession session) {
    parkingSessions.remove(session);
    session.setParkingMeter(null);
  }
}
