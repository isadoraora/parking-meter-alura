package com.alura.parkingmetersystem.controller;

import com.alura.parkingmetersystem.entity.model.VehicleDTO;
import com.alura.parkingmetersystem.entity.model.VehicleParkingSessionDTO;
import com.alura.parkingmetersystem.service.VehicleService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/blue_zone_parking/vehicle")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @GetMapping
  public ResponseEntity<Page<VehicleDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "10") Integer size
  ) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<VehicleDTO> vehicles = vehicleService.findAll(pageRequest);
    return ResponseEntity.ok(vehicles);
  }

  @GetMapping("/sessions")
  public ResponseEntity<Page<VehicleParkingSessionDTO>> findAllSessions(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                        @RequestParam(value = "linesPerPage", defaultValue = "20") Integer linesPerPage
  ) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage);
    var sessions = vehicleService.findAllSessions(pageRequest);
    return ResponseEntity.ok(sessions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<VehicleDTO> findById(@PathVariable Long id) {
    var vehicle = vehicleService.findById(id);
    return ResponseEntity.ok(vehicle);
  }

  @PostMapping
  public ResponseEntity<VehicleDTO> save(@Valid @RequestBody VehicleDTO vehicleDTO) {
    var savedVehicle = vehicleService.save(vehicleDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((savedVehicle.id())).toUri();
    return ResponseEntity.created(uri).body(savedVehicle);
  }

  @PutMapping("/{id}")
  public ResponseEntity<VehicleDTO> update(@RequestBody VehicleDTO vehicleDTO, @PathVariable Long id) {
    var updatedVehicle = vehicleService.update(id, vehicleDTO);
    return ResponseEntity.ok(updatedVehicle);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    vehicleService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
