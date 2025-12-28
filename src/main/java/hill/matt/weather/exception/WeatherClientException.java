package hill.matt.weather.exception;

public class WeatherClientException extends RuntimeException {

    private final int statusCode;
    private final String responseBody;

    public WeatherClientException(String message, int statusCode, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
