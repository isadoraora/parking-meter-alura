package com.alura.parkingmetersystem.service;

import com.alura.parkingmetersystem.entity.Vehicle;
import com.alura.parkingmetersystem.entity.model.VehicleDTO;
import com.alura.parkingmetersystem.entity.model.VehicleParkingSessionDTO;
import com.alura.parkingmetersystem.exception.GatewayException;
import com.alura.parkingmetersystem.exception.NotFoundException;
import com.alura.parkingmetersystem.mapper.VehicleMapper;
import com.alura.parkingmetersystem.repository.IVehicleRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {

  @Autowired
  private IVehicleRepository iServiceRepository;

  @Transactional(readOnly = true)
  public Page<VehicleDTO> findAll(PageRequest pageRequest) {
    var vehicles = iServiceRepository.findAll(pageRequest);
    return vehicles.map(VehicleMapper::fromEntity);
  }

  @Transactional(readOnly = true)
  public Page<VehicleParkingSessionDTO> findAllSessions(PageRequest pageRequest) {
    Page<Vehicle> vehicles = iServiceRepository.findAll(pageRequest);

    return vehicles.map(VehicleMapper::toVehicleParkingSessionDTO);
  }


  @Transactional(readOnly = true)
  public VehicleDTO findById(Long id) {
    var vehicle = iServiceRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Vehicle not found.")
    );

    return VehicleMapper.fromEntity(vehicle);
  }

  @Transactional
  public VehicleDTO save(VehicleDTO vehicleDTO) {
    Vehicle vehicle = VehicleMapper.toEntity(vehicleDTO);
    Vehicle savedVehicle = iServiceRepository.save(vehicle);
    return VehicleMapper.fromEntity(savedVehicle);
  }

  @Transactional
  public VehicleDTO update(Long id, VehicleDTO applianceDTO) {
    try {
      var vehicle = iServiceRepository.getReferenceById(id);
      VehicleMapper.mapperDtoToEntity(applianceDTO, vehicle);
      vehicle = iServiceRepository.save(vehicle);
      return VehicleMapper.fromEntity(vehicle);

    } catch (EntityNotFoundException enf) {
      throw new NotFoundException("Vehicle not found.");
    }
  }

  @Transactional
  public void delete(Long id) {
    try {
      iServiceRepository.deleteById(id);
    } catch (DataIntegrityViolationException die) {
      throw new GatewayException("Data integrity violation");
    }
  }
}
