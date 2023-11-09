package exception.handler;

import static java.lang.String.format;

import com.alura.parkingmetersystem.exception.ApiException;
import com.alura.parkingmetersystem.exception.GatewayException;
import com.alura.parkingmetersystem.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                final HttpHeaders headers,
                                                                final HttpStatus status,
                                                                final WebRequest request) {

    final UnprocessableEntity unprocessableEntity =
        new UnprocessableEntity(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid JSON body.", "invalid_json_body");

    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      unprocessableEntity.addValidationError(error.getField(), error.getDefaultMessage());
    }

    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      unprocessableEntity.addValidationError(error.getObjectName(), error.getDefaultMessage());
    }

    log.error(
        "[method:exception-handler][exception:MethodArgumentNotValidException][message:{}][error:{}][status:{}][cause:{}][request: {}]",
        unprocessableEntity.getMessage(),
        unprocessableEntity.getError(),
        unprocessableEntity.getStatus(),
        unprocessableEntity.getValidationErrorsMessage(),
        HandlerUtils.extractRequestContent(request),
        ex);

    return new ResponseEntity<>(unprocessableEntity, new HttpHeaders(), unprocessableEntity.getHttpStatus());
  }


  @ExceptionHandler(RestClientException.class)
  protected ResponseEntity<ApiError> handleRestClientException(final RestClientException exception) {
    log.error("External error: {}. Exception: {}", exception.getMessage(), exception, exception);

    final String message = format("External server error [%s]", exception.getMessage());

    final ApiError apiError = new ApiError("external_error", message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }

  @ExceptionHandler(ApiException.class)
  protected ResponseEntity<ApiError> handleApiException(ApiException e) {
    Integer statusCode = e.getStatusCode();
    boolean expected = HttpStatus.INTERNAL_SERVER_ERROR.value() > statusCode;
    if (expected) {
      log.warn("Internal Api warn. Status Code: " + statusCode, e);
    } else {
      log.error("Internal Api error. Status Code: " + statusCode, e);
    }

    ApiError apiError = new ApiError(e.getCode(), e.getDescription(), statusCode);
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }


  @ExceptionHandler(GatewayException.class)
  protected ResponseEntity<ApiError> handleGatewayException(GatewayException e) {
    Integer statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    log.error("Gateway Error. Status Code: " + statusCode, e);

    ApiError apiError = new ApiError(e.getErrorCode(), e.getMessage(), statusCode);
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }


  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ApiError> handleUnknownException(Exception e) {
    log.error("Internal error", e);

    ApiError apiError =
        new ApiError(
            "internal_error", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }

  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity<ApiError> handleNotFoundException(NotFoundException nfe) {
    log.error("Resource not found", nfe);

    ApiError apiError = new ApiError("not_found", nfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }
}
