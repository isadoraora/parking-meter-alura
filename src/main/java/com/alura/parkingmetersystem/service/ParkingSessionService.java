package com.alura.parkingmetersystem.service;

import com.alura.parkingmetersystem.entity.ParkingMeter;
import com.alura.parkingmetersystem.entity.ParkingSession;
import com.alura.parkingmetersystem.entity.Vehicle;
import com.alura.parkingmetersystem.entity.model.ParkingSessionDTO;
import com.alura.parkingmetersystem.exception.NotFoundException;
import com.alura.parkingmetersystem.mapper.ParkingSessionMapper;
import com.alura.parkingmetersystem.repository.IParkingMeterRepository;
import com.alura.parkingmetersystem.repository.IParkingSessionRepository;
import com.alura.parkingmetersystem.repository.IVehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkingSessionService {
  private final IParkingSessionRepository parkingSessionRepository;
  private final IVehicleRepository vehicleRepository;
  private final IParkingMeterRepository parkingMeterRepository;

  public ParkingSessionService(IParkingSessionRepository parkingSessionRepository, IVehicleRepository vehicleRepository,
                               IParkingMeterRepository parkingMeterRepository) {
    this.parkingSessionRepository = parkingSessionRepository;
    this.vehicleRepository = vehicleRepository;
    this.parkingMeterRepository = parkingMeterRepository;
  }

  @Transactional
  public ParkingSessionDTO save(ParkingSessionDTO parkingSessionDTO) {
    ParkingSession parkingSession = ParkingSessionMapper.toEntity(parkingSessionDTO);
    parkingSession = parkingSessionRepository.save(parkingSession);
    return ParkingSessionMapper.fromEntity(parkingSession);
  }


  @Transactional
  public ParkingSessionDTO update(Long id, ParkingSessionDTO parkingSessionDTO) {
    ParkingSession parkingSession = parkingSessionRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Parking session not found with id: " + id));
    ParkingSessionMapper.mapperDtoToEntity(parkingSessionDTO, parkingSession);
    ParkingSession updatedParkingSession = parkingSessionRepository.save(parkingSession);

    return ParkingSessionMapper.fromEntity(updatedParkingSession);
  }

  @Transactional
  public void delete(Long id) {
    if (!parkingSessionRepository.existsById(id)) {
      throw new NotFoundException("Parking session not found with id: " + id);
    }
    parkingSessionRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public Page<ParkingSessionDTO> findAll(PageRequest pageRequest) {
    Page<ParkingSession> sessions = parkingSessionRepository.findAll(pageRequest);
    return sessions.map(ParkingSessionMapper::fromEntity);
  }

  @Transactional(readOnly = true)
  public ParkingSessionDTO findById(Long id) {
    ParkingSession session = parkingSessionRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Parking session not found with id: " + id));
    return ParkingSessionMapper.fromEntity(session);
  }
}
