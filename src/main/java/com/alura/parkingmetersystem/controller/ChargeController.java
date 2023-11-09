package com.alura.parkingmetersystem.controller;

import com.alura.parkingmetersystem.entity.model.ChargeDTO;
import com.alura.parkingmetersystem.service.ChargeService;
import java.net.URI;
import javax.validation.Valid;
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
@RequestMapping("/blue_zone_parking/charge")
public class ChargeController {

  private final ChargeService chargeService;

  public ChargeController(ChargeService chargeService) {
    this.chargeService = chargeService;
  }

  @PostMapping
  public ResponseEntity<ChargeDTO> createCharge(@Valid @RequestBody ChargeDTO chargeDTO) {
    ChargeDTO newCharge = chargeService.createCharge(chargeDTO);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newCharge.id())
        .toUri();
    return ResponseEntity.created(location).body(newCharge);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ChargeDTO> getCharge(@PathVariable Long id) {
    ChargeDTO charge = chargeService.getChargeById(id);
    return ResponseEntity.ok(charge);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ChargeDTO> updateCharge(@PathVariable Long id, @Valid @RequestBody ChargeDTO chargeDTO) {
    ChargeDTO updatedCharge = chargeService.updateCharge(id, chargeDTO);
    return ResponseEntity.ok(updatedCharge);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCharge(@PathVariable Long id) {
    chargeService.deleteCharge(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<Page<ChargeDTO>> getAllCharges(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<ChargeDTO> charges = chargeService.getAllCharges(pageRequest);
    return ResponseEntity.ok(charges);
  }
}
