package hill.matt.weather.service;

import org.springframework.stereotype.Service;

import hill.matt.weather.config.WeatherApiConfig;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherClientService {

    private final WeatherApiConfig weatherApiConfig;

}
