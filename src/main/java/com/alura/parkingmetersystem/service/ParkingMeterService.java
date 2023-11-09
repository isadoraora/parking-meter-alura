package com.alura.parkingmetersystem.service;

import com.alura.parkingmetersystem.entity.ParkingMeter;
import com.alura.parkingmetersystem.entity.model.ParkingMeterParkingSessionDTO;
import com.alura.parkingmetersystem.mapper.ParkingMeterMapper;
import com.alura.parkingmetersystem.repository.IParkingMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkingMeterService {

  private final IParkingMeterRepository parkingMeterRepository;

  @Autowired
  public ParkingMeterService(IParkingMeterRepository parkingMeterRepository) {
    this.parkingMeterRepository = parkingMeterRepository;
  }

  @Transactional(readOnly = true)
  public Page<ParkingMeterParkingSessionDTO> findAllSessions(PageRequest pageRequest) {
    Page<ParkingMeter> parkingMeters = parkingMeterRepository.findAll(pageRequest);
    return parkingMeters.map(ParkingMeterMapper::toParkingMeterParkingSessionDTO);
  }
}
