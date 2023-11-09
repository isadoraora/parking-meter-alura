package com.alura.parkingmetersystem.entity.model;

public record ParkingMeterDTO(
    Long id,
    String code,
    String location,
    String status
) {
}
