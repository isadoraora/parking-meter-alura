package com.alura.parkingmetersystem.service;

import com.alura.parkingmetersystem.entity.User;
import com.alura.parkingmetersystem.entity.model.ParkingSessionDTO;
import com.alura.parkingmetersystem.entity.model.UserDTO;
import com.alura.parkingmetersystem.entity.model.UserParkingSessionDTO;
import com.alura.parkingmetersystem.entity.model.UserVehicleDTO;
import com.alura.parkingmetersystem.exception.GatewayException;
import com.alura.parkingmetersystem.exception.NotFoundException;
import com.alura.parkingmetersystem.mapper.ParkingSessionMapper;
import com.alura.parkingmetersystem.mapper.UserMapper;
import com.alura.parkingmetersystem.mapper.UserVehicleMapper;
import com.alura.parkingmetersystem.repository.IUserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final IUserRepository iUserRepository;

  @Transactional(readOnly = true)
  public Page<UserVehicleDTO> findAll(PageRequest pageRequest) {
    var address = iUserRepository.findAll(pageRequest);
    return address.map(UserVehicleMapper::fromEntity);
  }

  @Transactional(readOnly = true)
  public Page<UserParkingSessionDTO> findAllSessions(PageRequest pageRequest) {
    Page<User> users = iUserRepository.findAll(pageRequest);

    return users.map(user -> {
      Set<ParkingSessionDTO> parkingSessionDTOs = user.getVehicles().stream()
          .flatMap(vehicle -> vehicle.getParkingSessions().stream())
          .map(ParkingSessionMapper::fromEntity)
          .collect(Collectors.toSet());

      return new UserParkingSessionDTO(
          user.getId(),
          user.getName(),
          user.getCpf(),
          user.getCnh(),
          parkingSessionDTOs
      );
    });
  }

  @Transactional(readOnly = true)
  public UserVehicleDTO findById(Long id) {
    var address = iUserRepository.findById(id).orElseThrow(
        () -> new NotFoundException("User not found.")
    );

    return UserVehicleMapper.fromEntity(address);
  }

  @Transactional
  public UserDTO save(UserDTO userDTO) {
    var entity = UserMapper.toEntity(userDTO);
    var savedAddress = iUserRepository.save(entity);
    return UserMapper.fromEntity(savedAddress);
  }

  @Transactional
  public UserDTO update(Long id, UserDTO userDTO) {
    try {
      User user = iUserRepository.getReferenceById(id);
      UserMapper.mapperDtoToEntity(userDTO, user);
      user = iUserRepository.save(user);
      return UserMapper.fromEntity(user);

    } catch (EntityNotFoundException enf) {
      throw new NotFoundException("User not found, id: " + id);
    }
  }

  @Transactional
  public void delete(Long id) {
    try {
      iUserRepository.deleteById(id);
    } catch (DataIntegrityViolationException die) {
      throw new GatewayException("Data integrity violation");
    }
  }
}
