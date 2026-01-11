package hill.matt.weather.service;

import org.springframework.stereotype.Service;

import hill.matt.weather.model.ApiResponse;
import hill.matt.weather.model.visualcrossing.VisualCrossingWeatherApiResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherClientService {

    private final VisualCrossingWeatherApiService visualCrossingWeatherApiService;

    public ApiResponse retrieveWeatherForecast(String latitude, String longitude) {
        VisualCrossingWeatherApiResponse weatherResponse = visualCrossingWeatherApiService
                .retrieveWeatherForecastData(latitude, longitude);
        return new ApiResponse(weatherResponse);
    }

}
