package com.alura.parkingmetersystem.entity.model;

import java.time.LocalDateTime;
import lombok.Getter;

public record ParkingSessionDTO(

    @Getter
    Long id,
    Long vehicleId,
    Long parkingMeterId,
    LocalDateTime startTime,
    LocalDateTime endTime,
    ChargeDTO chargeDTO
) {

  public ParkingSessionDTO(Long id, Long vehicleId, Long parkingMeterId, LocalDateTime startTime, LocalDateTime endTime,
                           ChargeDTO chargeDTO) {
    this.id = id;
    this.vehicleId = vehicleId;
    this.parkingMeterId = parkingMeterId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.chargeDTO = chargeDTO;
  }
}
