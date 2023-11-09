package com.alura.parkingmetersystem.repository;

import com.alura.parkingmetersystem.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChargeRepository extends JpaRepository<Charge, Long> {
}
