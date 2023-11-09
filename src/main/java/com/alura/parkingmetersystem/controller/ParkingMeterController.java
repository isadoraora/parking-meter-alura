package com.alura.parkingmetersystem.controller;

import com.alura.parkingmetersystem.entity.model.ParkingMeterParkingSessionDTO;
import com.alura.parkingmetersystem.service.ParkingMeterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blue_zone_parking/parking_meter")
public class ParkingMeterController {

  private final ParkingMeterService parkingMeterService;

  public ParkingMeterController(ParkingMeterService parkingMeterService) {
    this.parkingMeterService = parkingMeterService;
  }

  @GetMapping("/sessions")
  public ResponseEntity<Page<ParkingMeterParkingSessionDTO>> findAllSessions(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "20") Integer linesPerPage) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage);
    Page<ParkingMeterParkingSessionDTO> sessions = parkingMeterService.findAllSessions(pageRequest);
    return ResponseEntity.ok(sessions);
  }
}

