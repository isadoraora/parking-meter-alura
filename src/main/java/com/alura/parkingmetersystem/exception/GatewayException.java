package com.alura.parkingmetersystem.exception;

public class GatewayException extends RuntimeException {

  protected String errorCode;

  public GatewayException(final String message, final String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public GatewayException(final String message, final String errorCode, final Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public GatewayException(String dataIntegrityViolation) {
  }

  public String getErrorCode() {
    return errorCode;
  }
}
