package com.alura.parkingmetersystem.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_parking_session")
public class ParkingSession {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vehicle_id", nullable = false)
  private Vehicle vehicle;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parking_meter_id", nullable = false)
  private ParkingMeter parkingMeter;

  @Column(nullable = false)
  private LocalDateTime startTime;

  private LocalDateTime endTime;

  @OneToOne(mappedBy = "parkingSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
  private Charge charge;

  public ParkingSession() {
  }

  public ParkingSession(Vehicle vehicle, ParkingMeter parkingMeter, LocalDateTime startTime) {
    this.vehicle = vehicle;
    this.parkingMeter = parkingMeter;
    this.startTime = startTime;
  }

  public void assignCharge(Charge charge) {
    this.charge = charge;
    charge.setParkingSession(this);
  }

  public void removeCharge() {
    if (charge != null) {
      charge.setParkingSession(null);
      this.charge = null;
    }
  }
}

