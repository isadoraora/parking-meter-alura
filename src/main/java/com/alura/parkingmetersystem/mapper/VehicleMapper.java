package com.alura.parkingmetersystem.mapper;

import com.alura.parkingmetersystem.entity.Vehicle;
import com.alura.parkingmetersystem.entity.model.ParkingSessionDTO;
import com.alura.parkingmetersystem.entity.model.VehicleDTO;
import com.alura.parkingmetersystem.entity.model.VehicleParkingSessionDTO;
import java.util.Set;
import java.util.stream.Collectors;

public class VehicleMapper {

  public static Vehicle toEntity(VehicleDTO vehicleDTO) {
    Vehicle vehicle = new Vehicle();
    vehicle.setModel(vehicleDTO.model());
    vehicle.setCarPlate(vehicleDTO.carPlate());
    return vehicle;
  }

  public static VehicleDTO fromEntity(Vehicle vehicle) {
    return new VehicleDTO(
        vehicle.getId(),
        vehicle.getModel(),
        vehicle.getCarPlate(),
        vehicle.getUser()
    );
  }

  public static VehicleParkingSessionDTO toVehicleParkingSessionDTO(Vehicle vehicle) {
    Set<ParkingSessionDTO> parkingSessionDTOs = vehicle.getParkingSessions().stream()
        .map(ParkingSessionMapper::fromEntity)
        .collect(Collectors.toSet());

    return new VehicleParkingSessionDTO(
        vehicle.getId(),
        vehicle.getModel(),
        vehicle.getCarPlate(),
        parkingSessionDTOs
    );
  }

  public static void mapperDtoToEntity(VehicleDTO vehicleDTO, Vehicle vehicle) {
    vehicle.setModel(vehicleDTO.model());
    vehicle.setCarPlate(vehicleDTO.carPlate());
  }
}
