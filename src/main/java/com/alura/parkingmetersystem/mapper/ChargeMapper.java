package com.alura.parkingmetersystem.mapper;

import com.alura.parkingmetersystem.entity.Charge;
import com.alura.parkingmetersystem.entity.model.ChargeDTO;

public class ChargeMapper {

  public static ChargeDTO fromEntity(Charge charge) {
    return new ChargeDTO(
        charge.getId(),
        charge.getAmount(),
        charge.getDateTime(),
        charge.getStatus().name(),
        charge.getParkingSession().getId()
    );
  }

  public static Charge toEntity(ChargeDTO chargeDTO) {
    Charge charge = new Charge();
    charge.setAmount(chargeDTO.amount());
    charge.setDateTime(chargeDTO.dateTime());
    charge.setStatus(Charge.ChargeStatus.valueOf(chargeDTO.status()));
    return charge;
  }
}
