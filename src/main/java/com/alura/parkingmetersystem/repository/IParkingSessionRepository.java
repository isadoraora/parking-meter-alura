package com.alura.parkingmetersystem.repository;

import com.alura.parkingmetersystem.entity.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParkingSessionRepository extends JpaRepository<ParkingSession, Long> {
}
