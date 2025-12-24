package hill.matt.weather.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hill.matt.weather.model.IncomingRequest;
import hill.matt.weather.service.WeatherClientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WeatherApiController {

    private final WeatherClientService service;

    @GetMapping("/getweather")
    public String getWeatherData(@ParameterObject IncomingRequest request) {
        return new String("Great Success");
    }

}
