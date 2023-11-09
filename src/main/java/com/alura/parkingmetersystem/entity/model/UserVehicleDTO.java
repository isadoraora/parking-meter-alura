package com.alura.parkingmetersystem.entity.model;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record UserVehicleDTO(
    Long userId,
    @NotBlank(message = "Name is required and cannot be blank.")
    String name,

    @CPF
    @NotNull(message = "CPF is required.")
    String cpf,

    @NotNull(message = "CNH is required.")
    String cnh,

    @Valid
    @NotNull(message = "Vehicle list cannot be null.")
    @NotEmpty(message = "Vehicle list cannot be empty.")
    List<VehicleDTO> vehicles) {
}
