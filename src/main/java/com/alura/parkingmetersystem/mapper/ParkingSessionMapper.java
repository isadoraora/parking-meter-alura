package com.alura.parkingmetersystem.mapper;

import com.alura.parkingmetersystem.entity.ParkingSession;
import com.alura.parkingmetersystem.entity.model.ParkingSessionDTO;

public class ParkingSessionMapper {

  public static ParkingSessionDTO fromEntity(ParkingSession parkingSession) {
    return new ParkingSessionDTO(
        parkingSession.getId(),
        parkingSession.getVehicle().getId(),
        parkingSession.getParkingMeter().getId(),
        parkingSession.getStartTime(),
        parkingSession.getEndTime(),
        ChargeMapper.fromEntity(parkingSession.getCharge())
    );
  }

  public static ParkingSession toEntity(ParkingSessionDTO parkingSessionDTO) {
    ParkingSession parkingSession = new ParkingSession();
    parkingSession.setStartTime(parkingSessionDTO.startTime());
    parkingSession.setEndTime(parkingSessionDTO.endTime());
    return parkingSession;
  }

  public static void mapperDtoToEntity(ParkingSessionDTO parkingSessionDTO, ParkingSession parkingSession) {
    parkingSession.setStartTime(parkingSessionDTO.startTime());
    parkingSession.setEndTime(parkingSessionDTO.endTime());
  }
}

