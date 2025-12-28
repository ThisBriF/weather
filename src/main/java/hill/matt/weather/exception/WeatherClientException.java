package hill.matt.weather.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class WeatherClientException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int statusCode;
    private final String responseBody;

    public WeatherClientException(String message, int statusCode, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }
    
}
