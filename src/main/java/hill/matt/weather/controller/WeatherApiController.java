package hill.matt.weather.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hill.matt.weather.model.ApiResponse;
import hill.matt.weather.model.IncomingRequest;
import hill.matt.weather.service.WeatherClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WeatherApiController {

    private final WeatherClientService service;

    @GetMapping("/getweather")
    public ResponseEntity<ApiResponse> getWeatherData(@ParameterObject @Valid IncomingRequest request) {
        return new ResponseEntity<>(service.retrieveWeatherForecase(request.latitude(), request.longitude()),
                HttpStatus.OK);
    }

}
