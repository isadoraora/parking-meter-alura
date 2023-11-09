package com.alura.parkingmetersystem.controller;

import com.alura.parkingmetersystem.entity.model.ParkingSessionDTO;
import com.alura.parkingmetersystem.service.ParkingSessionService;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blue_zone_parking/parking_session")
public class ParkingSessionController {

  private final ParkingSessionService parkingSessionService;

  @PostMapping
  public ResponseEntity<ParkingSessionDTO> save(@Valid @RequestBody ParkingSessionDTO parkingSessionDTO) {
    ParkingSessionDTO savedSession = parkingSessionService.save(parkingSessionDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedSession.getId()).toUri();
    return ResponseEntity.created(uri).body(savedSession);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ParkingSessionDTO> update(@PathVariable Long id,
                                                  @Valid @RequestBody ParkingSessionDTO parkingSessionDTO) {
    ParkingSessionDTO updatedSession = parkingSessionService.update(id, parkingSessionDTO);
    return ResponseEntity.ok(updatedSession);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    parkingSessionService.delete(id);
    return ResponseEntity.noContent().build();
  }


  @GetMapping
  public ResponseEntity<Page<ParkingSessionDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage
  ) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage);
    Page<ParkingSessionDTO> sessions = parkingSessionService.findAll(pageRequest);
    return ResponseEntity.ok(sessions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ParkingSessionDTO> findById(@PathVariable Long id) {
    ParkingSessionDTO session = parkingSessionService.findById(id);
    return ResponseEntity.ok(session);
  }
}
