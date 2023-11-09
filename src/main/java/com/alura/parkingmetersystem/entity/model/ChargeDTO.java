package com.alura.parkingmetersystem.entity.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ChargeDTO(
    Long id,
    BigDecimal amount,
    LocalDateTime dateTime,
    String status,
    Long parkingSessionId
) {
}
