package com.alura.parkingmetersystem.mapper;

import com.alura.parkingmetersystem.entity.User;
import com.alura.parkingmetersystem.entity.model.UserDTO;

public class UserMapper {

  public static User toEntity(UserDTO userDTO) {
    return new User(
        userDTO.name(),
        userDTO.cpf(),
        userDTO.cnh());
  }

  public static UserDTO fromEntity(User user) {
    return new UserDTO(
        user.getId(),
        user.getName(),
        user.getCpf(),
        user.getCnh()
    );
  }

  public static void mapperDtoToEntity(UserDTO userDTO, User user) {
    user.setName(userDTO.name());
    user.setCpf(userDTO.cpf());
    user.setCnh(userDTO.cnh());
  }
}
