package com.alura.parkingmetersystem.repository;

import com.alura.parkingmetersystem.entity.ParkingMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParkingMeterRepository extends JpaRepository<ParkingMeter, Long> {
}
