package com.alura.parkingmetersystem.service;

import com.alura.parkingmetersystem.entity.Charge;
import com.alura.parkingmetersystem.entity.ParkingSession;
import com.alura.parkingmetersystem.entity.model.ChargeDTO;
import com.alura.parkingmetersystem.exception.NotFoundException;
import com.alura.parkingmetersystem.mapper.ChargeMapper;
import com.alura.parkingmetersystem.repository.IChargeRepository;
import com.alura.parkingmetersystem.repository.IParkingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChargeService {

  private final IChargeRepository chargeRepository;
  private final IParkingSessionRepository parkingSessionRepository;

  @Autowired
  public ChargeService(IChargeRepository chargeRepository, IParkingSessionRepository parkingSessionRepository) {
    this.chargeRepository = chargeRepository;
    this.parkingSessionRepository = parkingSessionRepository;
  }

  @Transactional
  public ChargeDTO createCharge(ChargeDTO chargeDTO) {
    Charge charge = ChargeMapper.toEntity(chargeDTO);
    ParkingSession parkingSession = parkingSessionRepository.findById(chargeDTO.parkingSessionId())
        .orElseThrow(() -> new NotFoundException("Parking session not found with id: " + chargeDTO.parkingSessionId()));
    charge.setParkingSession(parkingSession);

    charge = chargeRepository.save(charge);
    return ChargeMapper.fromEntity(charge);
  }

  @Transactional(readOnly = true)
  public ChargeDTO getChargeById(Long id) {
    Charge charge = chargeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Charge not found with id: " + id));
    return ChargeMapper.fromEntity(charge);
  }

  @Transactional
  public ChargeDTO updateCharge(Long id, ChargeDTO chargeDTO) {
    Charge existingCharge = chargeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Charge not found with id: " + id));

    existingCharge.setAmount(chargeDTO.amount());
    existingCharge.setDateTime(chargeDTO.dateTime());
    existingCharge.setStatus(Charge.ChargeStatus.valueOf(chargeDTO.status()));

    Charge updatedCharge = chargeRepository.save(existingCharge);
    return ChargeMapper.fromEntity(updatedCharge);
  }

  @Transactional
  public void deleteCharge(Long id) {
    if (!chargeRepository.existsById(id)) {
      throw new NotFoundException("Charge not found with id: " + id);
    }
    chargeRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public Page<ChargeDTO> getAllCharges(PageRequest pageRequest) {
    Page<Charge> charges = chargeRepository.findAll(pageRequest);
    return charges.map(ChargeMapper::fromEntity);
  }
}
