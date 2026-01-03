package hill.matt.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WeatherClientException.class)
    public ResponseEntity<ProblemDetail> handleWeatherClientException(WeatherClientException ex) {
        // Map upstream errors to appropriate API responses
        HttpStatus upstreamStatus = HttpStatus.resolve(ex.getStatusCode());
        HttpStatus returnStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (upstreamStatus != null) {
            switch (upstreamStatus) {
                case BAD_REQUEST:
                    returnStatus = HttpStatus.BAD_REQUEST;
                    break;
                case UNAUTHORIZED:
                    returnStatus = HttpStatus.UNAUTHORIZED;
                    break;
                case NOT_FOUND:
                    returnStatus = HttpStatus.NOT_FOUND;
                    break;
                case TOO_MANY_REQUESTS:
                    returnStatus = HttpStatus.TOO_MANY_REQUESTS;
                    break;
                default:
                    break;
            }
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(returnStatus, ex.getMessage());
        problemDetail.setTitle("Weather Provider Error");
        problemDetail.setProperty("upstreamStatusCode", ex.getStatusCode());

        return ResponseEntity.status(returnStatus).body(problemDetail);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");
        problemDetail.setTitle("Invalid Request Parameters");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        problemDetail.setProperty("errors", errors);
        return ResponseEntity.badRequest().body(problemDetail);
    }

}
