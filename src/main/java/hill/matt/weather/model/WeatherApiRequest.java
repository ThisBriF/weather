package hill.matt.weather.model;

public record WeatherApiRequest(String latitude, String longitude, String metric) {
}