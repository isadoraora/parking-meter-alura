package com.alura.parkingmetersystem.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_charge")
public class Charge {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private LocalDateTime dateTime;

  @Enumerated(EnumType.STRING)
  private ChargeStatus status;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "session_id", referencedColumnName = "id")
  private ParkingSession parkingSession;

  public Charge() {
  }

  public enum ChargeStatus {
    PENDING,
    PAID,
    CANCELLED
  }
}
