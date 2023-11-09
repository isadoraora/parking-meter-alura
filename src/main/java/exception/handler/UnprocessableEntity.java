package exception.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.LinkedHashSet;
import java.util.Set;

public class UnprocessableEntity extends ApiError{

  private record ValidationError(String property, String message) {
  }

  private final Set<ValidationError> validationErrors = new LinkedHashSet<>();

  public UnprocessableEntity(final int status, final String message, final String error) {
    super(error, message, status);
  }

  public void addValidationError(final String property, final String message) {
    this.validationErrors.add(new ValidationError(property, message));
  }

  @JsonIgnore
  public String getValidationErrorsMessage() {
    return validationErrors.stream()
        .map(v -> "[property:" + v.property + "][message:" + v.message + "]")
        .reduce("", String::concat);
  }
}
