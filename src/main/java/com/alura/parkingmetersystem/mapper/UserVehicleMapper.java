package com.alura.parkingmetersystem.mapper;

import com.alura.parkingmetersystem.entity.User;
import com.alura.parkingmetersystem.entity.model.UserVehicleDTO;
import com.alura.parkingmetersystem.entity.model.VehicleDTO;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserVehicleMapper {

  public static UserVehicleDTO fromEntity(User user) {
    List<VehicleDTO> vehicleDTOs = user.getVehicles() != null
        ? user.getVehicles().stream()
        .map(VehicleMapper::fromEntity)
        .collect(Collectors.toList())
        : Collections.emptyList();

    return new UserVehicleDTO(
        user.getId(),
        user.getName(),
        user.getCpf(),
        user.getCnh(),
        vehicleDTOs
    );
  }
}
