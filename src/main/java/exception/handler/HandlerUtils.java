package exception.handler;

import java.nio.charset.StandardCharsets;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class HandlerUtils {

  private HandlerUtils() {
  }

  public static String extractRequestContent(WebRequest webRequest) {
    if (webRequest instanceof ServletWebRequest) {

      ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
      ContentCachingRequestWrapper nativeRequest = servletWebRequest.getNativeRequest(ContentCachingRequestWrapper.class);

      if (nativeRequest != null && nativeRequest.getContentAsByteArray() != null) {
        return new String(nativeRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
      }
    }
    return "Request content not available";
  }
}
