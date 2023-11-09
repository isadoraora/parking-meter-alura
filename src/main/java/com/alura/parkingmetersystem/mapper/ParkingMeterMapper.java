package com.alura.parkingmetersystem.mapper;

import com.alura.parkingmetersystem.entity.ParkingMeter;
import com.alura.parkingmetersystem.entity.model.ParkingMeterParkingSessionDTO;
import com.alura.parkingmetersystem.entity.model.ParkingSessionDTO;
import java.util.Set;
import java.util.stream.Collectors;

public class ParkingMeterMapper {

  public static ParkingMeterParkingSessionDTO toParkingMeterParkingSessionDTO(ParkingMeter parkingMeter) {
    Set<ParkingSessionDTO> parkingSessionDTOs = parkingMeter.getParkingSessions().stream()
        .map(ParkingSessionMapper::fromEntity)
        .collect(Collectors.toSet());

    return new ParkingMeterParkingSessionDTO(
        parkingMeter.getId(),
        parkingMeter.getCode(),
        parkingMeter.getLocation(),
        parkingMeter.getStatus().name(),
        parkingSessionDTOs
    );
  }
}
