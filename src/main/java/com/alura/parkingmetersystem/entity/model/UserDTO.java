package com.alura.parkingmetersystem.entity.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record UserDTO(
    Long id,
    @NotBlank(message = "Name is required and cannot be blank.")
    String name,

    @CPF
    @NotNull(message = "CPF is required.")
    String cpf,

    @NotNull(message = "CNH is required.")
    String cnh
) {
}
