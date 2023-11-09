package com.alura.parkingmetersystem.entity.model;

import java.util.Set;

public record ParkingMeterParkingSessionDTO(
    Long id,
    String code,
    String location,
    String status,
    Set<ParkingSessionDTO> parkingSessions) {
}

