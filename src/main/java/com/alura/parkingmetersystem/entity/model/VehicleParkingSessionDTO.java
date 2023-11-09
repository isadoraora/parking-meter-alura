package com.alura.parkingmetersystem.entity.model;

import java.util.Set;

public record VehicleParkingSessionDTO(
    Long vehicleId,
    String carPlate,
    String model,
    Set<ParkingSessionDTO> parkingSessions
) {
}
