package com.alura.parkingmetersystem.exception;

public class NotFoundException extends DomainException {
  public NotFoundException(String message) {
    super(message);
  }
}
