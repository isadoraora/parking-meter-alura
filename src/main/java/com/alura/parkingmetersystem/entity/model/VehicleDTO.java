package com.alura.parkingmetersystem.entity.model;

import com.alura.parkingmetersystem.entity.User;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record VehicleDTO(

    Long id,

    String model,

    @NotNull(message = "Car plate is required.")
    @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}$", message = "Car plate must be in the format AAA-9999.")
    String carPlate,
    User userId) {
}
